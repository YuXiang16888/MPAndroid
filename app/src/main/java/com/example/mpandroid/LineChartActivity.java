package com.example.mpandroid;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {

    private LineChart mLineChart;
    private LineData mLineData;
    private LineDataSet mLineDataSet;
    private Runnable mRunnable;
    private Handler mHandler;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        mLineChart = findViewById(R.id.mp_chart_line);
        MpChart.lineMpChart(mLineChart,true,0,0);
        initRunnable();
    }

    private void addEntryPoint() {
        float random = (float)(Math.random() * 10);
        MpChart.lineMpChart(mLineChart,false,i,random);
        i++;
    }

    private void initRunnable(){
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                addEntryPoint();
                if(i < 100)
                    mHandler.postDelayed(mRunnable,100);
            }
        };
        mHandler.postDelayed(mRunnable,100);
    }

}
