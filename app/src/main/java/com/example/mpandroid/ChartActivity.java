package com.example.mpandroid;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

public class ChartActivity extends AppCompatActivity {

    private LineChart mLineChart;
    private BarChart mBarChart;
    private PieChart mPieChart;
    private Runnable mRunnable;
    private Handler mHandler;
    private String mChartName;
    private boolean initFlag;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        mChartName = getIntent().getStringExtra("ChartMode");
        mLineChart = findViewById(R.id.mp_chart_line);
        mBarChart = findViewById(R.id.mp_chart_bar);
        mPieChart = findViewById(R.id.mp_chart_pie);
        processChart();
        initRunnable();
    }


    private void processChart(){

        if(mChartName.equals("Line Chart")){
            mBarChart.setVisibility(View.GONE);
            mPieChart.setVisibility(View.GONE);
            MpChart.lineMpChart(mLineChart,0,0);
        }else if(mChartName.equals("Bar Chart")){
            mLineChart.setVisibility(View.GONE);
            mPieChart.setVisibility(View.GONE);
            MpChart.barMpChart(mBarChart,0,0);
        }else if (mChartName.equals("Pie Chart")){
            mLineChart.setVisibility(View.GONE);
            mBarChart.setVisibility(View.GONE);
            MpChart.pieMpChart(mPieChart,"",0);
        }
    }

    private void addEntryPoint() {
        float random = (float)(Math.random() * 10 + 2);
        if(mChartName.equals("Line Chart")){
            MpChart.lineMpChart(mLineChart,i,random);
        }else if(mChartName.equals("Bar Chart")){
            MpChart.barMpChart(mBarChart,i,random);
        }else if (mChartName.equals("Pie Chart")){
            MpChart.pieMpChart(mPieChart,"Part" + i,random);
        }
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
