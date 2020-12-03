package com.example.myapplication;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PmView extends View {

    private static final String PROPERTY_SWEEP = "PROPERTY_SWEEP";
    private static final String PROPERTY_TRANSACTION = "PROPERTY_TRANSACTION";
    private Paint pmCircle;
    private Paint pmCircleIn;
    private Paint pmEye;
    private int dotNum = 3;
    private int colorCircle;
    private int colorEye;

    private float left;
    private float right;
    private float top;
    private float bottom;

    private float startAngle;
    private float sweepAngle;

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

    public void createAnimation() {
        PropertyValuesHolder propertySweep = PropertyValuesHolder.ofInt(PROPERTY_SWEEP, 0, 30);
        PropertyValuesHolder propertyTransaction = PropertyValuesHolder.ofInt(PROPERTY_TRANSACTION, 0, 200);
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(propertySweep, propertyTransaction);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = (int) animation.getAnimatedValue(PROPERTY_SWEEP);
                sweepAngle = 360 - startAngle*2;
                left = (int) animation.getAnimatedValue(PROPERTY_TRANSACTION);
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float square = 200;
        left = 50;
        right = 50 + square;
        top = 50;
        bottom = 50 + square;
        createAnimation();
        canvas.drawArc(left, top, right, bottom, startAngle, sweepAngle, true, pmCircle);

        float eyeCx = left + 100;
        float eyeCy = top + 60;
        canvas.drawCircle(eyeCx, eyeCy, 15, pmEye);

        //draw dot
        for (int i = 1; i <= dotNum; i++) {
            canvas.drawCircle(eyeCx + 150 * i, eyeCy + 30, 30, pmCircle);
        }
    }
}
