package com.jara.mymeterialdesign.commonview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jara.mymeterialdesign.R;

/**
 * 圆形进度条
 * Created by jara on 2017-7-7.
 */

public class CircleProgressView extends View {

    /** 当前进度 */
    private int mCurrent;
    private Paint mPaintOut;
    private Paint mPaintCurrent;
    private Paint mPaintText;
    /** 画笔宽度 */
    private float mPaintWidth;
    private int mPaintColor = Color.RED;
    private int mTextColor = Color.BLACK;
    private float mTextSize;
    /** 开始位置 */
    private int location;
    /** 开始角度 */
    private float startAngle;

    private OnLoadingCompleteListener completeListener;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        location = array.getInt(R.styleable.CircleProgressView_location, 1);
        mPaintWidth = array.getDimension(R.styleable.CircleProgressView_progress_paint_width, dip2px(context, 4));
        mPaintColor = array.getColor(R.styleable.CircleProgressView_progress_paint_color, mPaintColor);
        mTextSize = array.getDimension(R.styleable.CircleProgressView_progress_text_size, dip2px(context, 18));//默认18sp
        mTextColor = array.getColor(R.styleable.CircleProgressView_progress_text_color, mTextColor);
        array.recycle();

        // 画笔 画整个背景圆弧
        mPaintOut = new Paint();
        mPaintOut.setAntiAlias(true);
        mPaintOut.setStrokeWidth(mPaintWidth);
        mPaintOut.setColor(Color.GRAY);
        mPaintOut.setStyle(Paint.Style.STROKE);
        mPaintOut.setStrokeCap(Paint.Cap.ROUND);

        //画笔 绘制进度
        mPaintCurrent = new Paint();
        mPaintCurrent.setAntiAlias(true);
        mPaintCurrent.setStrokeWidth(mPaintWidth);
        mPaintCurrent.setColor(mPaintColor);
        mPaintCurrent.setStrokeCap(Paint.Cap.ROUND);
        mPaintCurrent.setStyle(Paint.Style.STROKE);

        //画笔 绘制字体
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(mTextColor);
        mPaintText.setTextSize(mTextSize);

        if (location == 1) {
            startAngle = -180;
        } else if (location == 2) {
            startAngle = -90;
        } else if (location == 3) {
            startAngle = 0;
        } else if (location == 4) {
            startAngle = 90;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取控件的宽和高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width >= height ? height : width;
        setMeasuredDimension(size, size);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制背景圆弧
        RectF rectF = new RectF(mPaintWidth / 2, mPaintWidth / 2, getWidth() - mPaintWidth / 2
                , getHeight() - mPaintWidth / 2);
        canvas.drawArc(rectF, 0, 360, false, mPaintOut);

        //绘制当前进度
        float sweepAngle = 360 * mCurrent / 100;
        canvas.drawArc(rectF, startAngle, sweepAngle, false, mPaintCurrent);

        //绘制进度数字
        String text = mCurrent + "%";
        float textWidth = mPaintText.measureText(text, 0 , text.length());
        float dx = getWidth() / 2 - textWidth / 2;
        Paint.FontMetricsInt fontMetricsInt = mPaintText.getFontMetricsInt();
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        float baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, dx, baseLine, mPaintText);

        if (completeListener != null && mCurrent == 100) {
            completeListener.complete();
        }
    }

    public int getmCurrent() {
        return mCurrent;
    }

    public void setmCurrent(int mCurrent) {
        this.mCurrent = mCurrent;
        //刷新界面
        invalidate();
    }

    public void setOnLoadingCompleteListener(OnLoadingCompleteListener listener) {
        this.completeListener = listener;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface OnLoadingCompleteListener {
        void complete();
    }
}
