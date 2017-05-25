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
 * A custom view representing a game board for a tabletop role-playing game.
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

    /**
     * Add a map to the view to be displayed.
     * @param map The map to be displayed in the view.
     */
    public void setMap(Map map) {
        mMap = map;
        invalidate();
    }

    /**
     * Draw the map. Includes a grid of all of the spaces.
     * Fill the spaces with empty tiles by default.
     * @param canvas The canvas on which objects are drawn.
     */
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

    /**
     * Draw a tile on the canvas. Empty tiles are drawn as gray squares. Player tiles are blue squares.
     * Non player tiles are red squares.
     * @param tile The tile to be drawn.
     * @param canvas The canvas on which tiles are drawn.
     * @param x The X coordinate of the tile.
     * @param y The Y coordinate of the tile.
     */
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

    /**
     * Set up painters used by the canvas.
     */
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

    /**
     * Set the listener that handles what happens when a tile is clicked.
     * @param onTileClickedListener The click handler listener.
     */
    public void setTileClickedListener(OnTileClickedListener onTileClickedListener) {
        mListener = onTileClickedListener;
    }

    /**
     * Handles what happens on a touch event. If the map is being edited, the DM is placing objects
     * on the map. Otherwise player or non-player characters are being moved.
     * @param event The event that is handled.
     * @return a boolean representing whether the touch event is consumed.
     */
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

    /**
     * Set up a FireBase listener for changes to the map. Any changes made by player activity are
     * reflected across sessions for all players.
     */
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
