package com.shawn.graphics.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.shawn.R;

/**
 * Created by shawn on 14/10/24.
 */
public class AlphaCircleProgressDrawable extends Drawable {

    private static final String TAG = "CircleDrawable";

    /**
     * the progress of the circle
     */
    private float mProgress = 0.8f;

    /**
     * the alpha of the circle
     */
    private int mAlpha = 60;

    /**
     * the paint to draw the circle
     */
    private Paint mPaint;

    /**
     * the color of the circle
     */
    private int mColor;

    /**
     * the width of the circle
     */
    private int ringWidth;

    /**
     * Rectangle where the filling ring will be drawn into.
     */
       protected RectF arcElements;

    public static String PROGRESS_PROPERTY = "progress";

    private Context mContext;

    private Bitmap mCirclePoint;

    public static final int RED_STYLE = 1;
    public static final int BLUE_STYLE = 2;
    public static final int YELLOW_STYLE = 3;

    private int mStyle;

    public AlphaCircleProgressDrawable(Context context, int style , float progress) {
        if (progress > 1.0f || progress < 0.0f){
            throw new RuntimeException("progress:"+progress+" is invalid,please check the progress value");
        }
        this.mContext = context;
        this.mStyle = style;
        this.mProgress = progress;
        init();
    }

    private void init() {

        switch (mStyle){
            case RED_STYLE:
                mCirclePoint = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.circle_red);
                mColor = mContext.getResources().getColor(R.color.circle_red);
                break;

            case YELLOW_STYLE:
                mCirclePoint = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.circle_red);
                mColor = mContext.getResources().getColor(R.color.circle_yellow);
                break;

            case BLUE_STYLE:
            default:
                mCirclePoint = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.circle_red);
                mColor = mContext.getResources().getColor(R.color.circle_blue);
                break;
        }

        ringWidth = mContext.getResources().getDimensionPixelOffset(R.dimen.circle_width);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAlpha(mAlpha);
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        arcElements = new RectF();

    }

    @Override
    public void draw(Canvas canvas) {

        final Rect bounds = getBounds();

        /**
         * calculate rect size
         */
        int size = bounds.height() > bounds.width() ? bounds.width() : bounds.height();

        /**
         * calculate radius of the circle
         */
        float radius = (size-ringWidth*2)/2;

        /**
         * calculate (x,y) of the start point
         */
        float offsetX = ringWidth;
        float offsetY = ringWidth;

        /**
         * calculate left and bottom point of the rect for rendering circle
         */
        float arcX0 = offsetX;
        float arcY0 = offsetY;
        float arcX = offsetX + radius*2;
        float arcY = offsetY + radius*2;

        arcElements.set(arcX0, arcY0, arcX, arcY);

        /**
         * center point (x,y)
         */
        double centerX = arcElements.centerX();
        double centerY = arcElements.centerY();

//        Log.i(TAG,"radius:"+radius);
//        Log.i(TAG,"left:("+arcX+","+arcY+")");
//        Log.i(TAG,"right:("+arcX0+","+arcY0+")");
//        Log.i(TAG,"center:("+centerX+","+centerY+")");


        /**
         * render the circle with gradually color
         */
        double i = 0.0f;
        float dis = (float) (360.0 / 140.0);
        for (; i <= mProgress * 360; i += dis) {
            canvas.save();
            mPaint.setAlpha(mAlpha++);
            canvas.drawArc(arcElements, (float)i, dis, false, mPaint);
            canvas.restore();
        }

//        double pointX = 0.0f;
//        double pointY = 0.0f;
//        Log.e(TAG,"i:" + i);
//        if (i <= 90.0f) {
//            pointX = centerX + radius * Math.cos(i);
//            pointY = centerY + radius * Math.sin(i);
//        } else if (i > 90.0f && i <= 180.0f) {
//            pointX = centerX - radius * Math.cos(180.0f - i);
//            pointY = centerY + radius * Math.sin(180.0f - i);
//        } else if (i > 180.0f && i <= 270.0f) {
//            pointX = centerX - radius * Math.cos(i - 180.0f);
//            pointY = centerY - radius * Math.sin(i - 180.0f);
//        } else if (i > 270.0f && i <= 360.0f) {
//            pointX = centerX + radius * Math.cos(360.0f - i);
//            pointY = centerY - radius * Math.sin(360.0f - i);
//        }

//        Log.d(TAG,"PROGRESS:"+mProgress);

//        Log.i(TAG,"("+pointX+","+pointY+")");
//        double x2 = centerX + outerRadius * Math.cos(i);
//        double y2 = centerY + outerRadius * Math.sin(i);

        /**
         * start point bitmap radius
         */
        double pointRadius = mContext.getResources().getDimensionPixelSize(R.dimen.circle_point_width) / 2;

        /**
         * render bitmap
         */
        canvas.rotate((float) i,(float)centerX,(float)centerY);
//        canvas.drawBitmap(mCirclePoint, (float) (pointX - pointRadius), (float) (pointY - pointRadius), new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.drawBitmap(mCirclePoint,(float)(centerX+radius-pointRadius),(float)(centerY-pointRadius),new Paint(Paint.ANTI_ALIAS_FLAG));
        mAlpha = 60;
//        ringWidth = mContext.getResources().getDimensionPixelOffset(R.dimen.circle_width);
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidateSelf();
    }


    @Override
    public void setAlpha(int i) {
    }


    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
