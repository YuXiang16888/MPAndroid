package com.example.mpandroid;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
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
    private List<Entry> mListEntry;
    private Runnable mRunnable;
    private Handler mHandler;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        mLineChart = findViewById(R.id.mp_chart_line);
        settingChart();
        mListEntry = new ArrayList<Entry>();
        mLineData = new LineData();
        mLineDataSet = new LineDataSet(mListEntry,"LineChart");
        mLineDataSet.setHighlightEnabled(true);
        mLineDataSet.setHighLightColor(Color.parseColor("#FF0000"));
        mLineData.addDataSet(mLineDataSet);
        mLineChart.setData(mLineData);
        initRunnable();
    }

    private void addEntryPoint() {
        mListEntry.add(new Entry(i,(float)Math.random() * 10));
        mLineDataSet.notifyDataSetChanged();
        if(mLineDataSet.getEntryCount() > 50){
            mLineDataSet.removeFirst();
        }
        mLineData.notifyDataChanged();
        mLineChart.notifyDataSetChanged();
        mLineChart.moveViewToX(i);
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

    private void settingChart() {
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        //mLineChart.setScaleXEnabled(true);
        //mLineChart.setScaleYEnabled(true);
        mLineChart.setPinchZoom(false);
        mLineChart.setDoubleTapToZoomEnabled(false);
        mLineChart.setDragDecelerationEnabled(true);
        mLineChart.setDragDecelerationFrictionCoef(0.9f);
        mLineChart.getAxisRight().setEnabled(false);//取消右边y轴;
        mLineChart.setHighlightPerDragEnabled(true);
        mLineChart.setHighlightPerTapEnabled(true);
        mLineChart.setMaxHighlightDistance(500f);
    }


}
