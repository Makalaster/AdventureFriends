package com.makalaster.adventurefriends.model.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makalaster.adventurefriends.dm.CampaignHolder;

import java.util.ArrayList;

/**
 * Created by Makalaster on 5/18/17.
 */

public class MapView extends View {
    private static final String TAG = "MapView";

    private Paint mPlayerPaint, mTilePaint, mNonPlayerPaint, mLinePaint;
    private Map mMap;
    private int mWidth, mHeight;
    private boolean mEditMode;
    private OnTileClickedListener mListener;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mEditMode = false;

        init();
        setupMapListener();
    }

    public void setMap(Map map) {
        mMap = map;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int numX = Map.TILE_WIDTH;
        int numY = Map.TILE_HEIGHT;

        for (int i = 1; i <= numX; i++) {
            int x = i * (mWidth / numX);
            canvas.drawLine(x, 0, x, mHeight, mLinePaint);
        }

        for (int i = 0; i < numY; i++) {
            int y = i * (mHeight / numY);
            canvas.drawLine(0, y, mWidth, y, mLinePaint);
        }

        ArrayList<ArrayList<Tile>> tiles = mMap.getTiles();

        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                renderTile(tiles.get(i).get(j), canvas, i, j);
            }
        }
    }

    private void renderTile(Tile tile, Canvas canvas, int x, int y) {
        float r = 1 + (mWidth / Map.TILE_WIDTH) * x;
        float l = r + (mWidth / Map.TILE_WIDTH) - 1;
        float t = 1 + (mHeight / Map.TILE_HEIGHT) * y;
        float b = t + (mHeight / Map.TILE_HEIGHT) - 1;

        RectF mapTile = new RectF(l, t, r, b);

        if (tile.containsNonPlayer()) {
            canvas.drawRect(mapTile, mNonPlayerPaint);
        } else if (tile.containsPlayer()) {
            canvas.drawRect(mapTile, mPlayerPaint);
        } else {
            canvas.drawRect(mapTile, mTilePaint);
        }
    }

    private void init() {
        mPlayerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPlayerPaint.setColor(Color.BLUE);
        mPlayerPaint.setStyle(Paint.Style.FILL);

        mTilePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTilePaint.setColor(Color.LTGRAY);
        mTilePaint.setStyle(Paint.Style.FILL);

        mNonPlayerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNonPlayerPaint.setColor(Color.RED);
        mNonPlayerPaint.setStyle(Paint.Style.FILL);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLACK);
    }

    public void setTileClickedListener(OnTileClickedListener onTileClickedListener) {
        mListener = onTileClickedListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x, y;

        x = (int) (event.getX() * Map.TILE_WIDTH) / mWidth;
        y = (int) (event.getY() * Map.TILE_HEIGHT) / mHeight;

        if (mEditMode) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mListener.onDmTileClicked(mMap.getTile(x, y));
                    break;
            }
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mListener.onPlayerTileClicked(mMap.getTile(x, y));
                    break;
            }
        }

        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }

    public boolean isEditMode() {
        return mEditMode;
    }

    public void setEditMode(boolean editMode) {
        mEditMode = editMode;
    }

    public void setupMapListener() {
        String currentCampaignId = CampaignHolder.getInstance().getCampaignId();

        DatabaseReference currentMapReference = FirebaseDatabase.getInstance()
                .getReference("campaigns/" + currentCampaignId + "/currentMap");
        currentMapReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map newMap = dataSnapshot.getValue(Map.class);
                if (newMap != null) {
                    mMap.setTiles(newMap.getTiles());
                    invalidate();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
