package com.makalaster.adventurefriends.model.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Makalaster on 5/18/17.
 */

public class MapView extends View {
    private Paint mPlayerPaint, mTilePaint, mNonPlayerPaint, mLinePaint;
    private int mWidth, mHeight;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int numX = 8;
        int numY = 10;

        for (int i = 1; i <= numX; i++) {
            int x = i * (mWidth / numX);
            canvas.drawLine(x, 0, x, mHeight, mLinePaint);
        }

        for (int i = 0; i < numY; i++) {
            int y = i * (mHeight / numY);
            canvas.drawLine(0, y, mWidth, y, mLinePaint);
        }

        //canvas.drawText("Width: " + mWidth, 500, 500, mPlayerPaint);
        //canvas.drawText("Height: " + mHeight, 500, 600, mPlayerPaint);

        //canvas.drawOval(10, 20, 30, 40, mPlayerPaint);

        //canvas.drawRect(50, 60, 70, 80, mTilePaint);

        //canvas.drawCircle(100, 150, 30, mNonPlayerPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }
}
