package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PmView extends View {

    private Paint pmCircle;
    private Paint pmCircleIn;
    private Paint pmEye;
    private int dotNum = 3;
    private int colorCircle;
    private int colorEye;

    public PmView(Context context) {
        super(context);
        initViews();
    }

    public PmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initViews();
    }

    public PmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initViews();
    }

    public PmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
        initViews();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PmView, 0, 0);
        try {
            dotNum = ta.getInteger(R.styleable.PmView_dotNum, dotNum);
            colorCircle = ta.getColor(R.styleable.PmView_circleColor, getResources().getColor(R.color.pacman_circle));
            colorEye = ta.getColor(R.styleable.PmView_eyeColor, getResources().getColor(R.color.pacman_eye));
        } finally {
            ta.recycle();
        }
    }

    private void initViews() {
        pmCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        pmCircle.setColor(colorCircle);
        pmEye = new Paint(Paint.ANTI_ALIAS_FLAG);
        pmEye.setColor(colorEye);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float square = 200;
        float left = 50;
        float right = 50 + square;
        float top = 50;
        float bottom = 50 + square;
        canvas.drawArc(left, top, right, bottom, 40, 280, true, pmCircle);

        float eyeCx = left + 100;
        float eyeCy = top + 60;
        canvas.drawCircle(eyeCx, eyeCy, 15, pmEye);

        //draw dot
        for (int i = 1; i <= dotNum; i++) {
            canvas.drawCircle(eyeCx + 150 * i, eyeCy + 30, 30, pmCircle);
        }
    }
}
