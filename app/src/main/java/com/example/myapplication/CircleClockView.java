package com.example.myapplication;

import android.content.Context;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CircleClockView extends ConstraintLayout {
    public CircleClockView(Context context) {
        super(context);
        init(context);

    }

    public CircleClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init(context);
    }

    public CircleClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        inflate(context, R.layout.layout_circle_clock, this);
        TextView tvHour = findViewById(R.id.tvHour);
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                DateFormat df = new SimpleDateFormat("hh:mm:ss a");
                String dateHour = df.format(Calendar.getInstance().getTime());
                tvHour.setText(dateHour);
            }
        };

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        sleep(500);
                        handler.post(r);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void init(Context context) {
    }
}
