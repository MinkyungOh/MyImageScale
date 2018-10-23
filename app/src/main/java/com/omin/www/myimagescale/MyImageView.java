package com.omin.www.myimagescale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class MyImageView extends View {
    private Drawable image;
    private ScaleGestureDetector gdetector;
    private float scale = 1.0f;

    public MyImageView(Context context) {
        super(context);
        image = context.getDrawable(R.drawable.froyo);
        setFocusable(true);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        gdetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();
                if(scale <0.1f) scale = 0.1f;
                if(scale >10.0f) scale = 10.0f;
                invalidate();
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale, scale);
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gdetector.onTouchEvent(event);
        invalidate();
        return true;
    }
}
