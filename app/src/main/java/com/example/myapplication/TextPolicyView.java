package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TextPolicyView extends ConstraintLayout {

    private String textNamePolicy;
    private String textContentPolicy;

    public TextPolicyView(Context context) {
        super(context);
        initViews(context);
    }

    public TextPolicyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initViews(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {

        inflate(context, R.layout.layout_text_policy, this);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvContent = (TextView) findViewById(R.id.tvContent);

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextPolicyView, 0, 0);
        try {
            textNamePolicy = ta.getText(R.styleable.TextPolicyView_textNamePolicy).toString();
            textContentPolicy = ta.getText(R.styleable.TextPolicyView_textContentPolicy).toString();
        } finally {
            ta.recycle();
        }

        tvName.setText(textNamePolicy);
        tvContent.setText(textContentPolicy);
    }

    private void initViews(Context context) {

    }

}
