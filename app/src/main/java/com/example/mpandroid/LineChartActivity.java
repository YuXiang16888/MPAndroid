package com.example.mpandroid;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {

    private LineChart mLineChart;
    private BarChart mBarChart;
    private PieChart mPieChart;
    private Runnable mRunnable;
    private Handler mHandler;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        mLineChart = findViewById(R.id.mp_chart_line);
        mBarChart = findViewById(R.id.mp_chart_bar);
        mPieChart = findViewById(R.id.mp_chart_pie);
        mLineChart.setVisibility(View.GONE);
        mBarChart.setVisibility(View.GONE);
        //MpChart.lineMpChart(mLineChart,0,0);
        //MpChart.barMpChart(mBarChart,0,0);
        MpChart.pieMpChart(mPieChart,"",0);
        initRunnable();
    }

    private void addEntryPoint() {
        float random = (float)(Math.random() * 10 + 2);
        //MpChart.lineMpChart(mLineChart,i,random);
        //MpChart.barMpChart(mBarChart,i,random);
        MpChart.pieMpChart(mPieChart,"Part" + i,random);
        i++;
    }

    private void initRunnable(){
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                addEntryPoint();
                if(i < 10)
                    mHandler.postDelayed(mRunnable,100);
            }
        };
        mHandler.postDelayed(mRunnable,100);
    }

}
