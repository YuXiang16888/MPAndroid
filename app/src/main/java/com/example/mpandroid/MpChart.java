package com.example.mpandroid;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;
import java.util.List;

public class MpChart {

    //LineMPChart

    public static void lineMpChart(LineChart lineChart, float time, float value) {

        if (lineChart.getData() == null) {
            LineData lineData = new LineData();
            lineChart.setData(lineData);

            lineChart.setTouchEnabled(true);
            lineChart.setDragEnabled(true);
            lineChart.setScaleEnabled(true);
            //mLineChart.setScaleXEnabled(true);
            //mLineChart.setScaleYEnabled(true);
            lineChart.setPinchZoom(false);
            lineChart.setDoubleTapToZoomEnabled(false);
            lineChart.setDragDecelerationEnabled(true);
            lineChart.setDragDecelerationFrictionCoef(0.9f);
            lineChart.getAxisRight().setEnabled(false);//取消右边y轴;
            lineChart.setHighlightPerDragEnabled(true);
            lineChart.setHighlightPerTapEnabled(true);
            lineChart.setMaxHighlightDistance(500f);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(12);
            xAxis.setGranularity(2);
            xAxis.setTextColor(Color.RED);
            xAxis.setGridColor(Color.RED);
            xAxis.setEnabled(true);

            YAxis yAxis = lineChart.getAxisLeft();
            yAxis.setTextColor(Color.RED);
            yAxis.setGridColor(Color.RED);
            yAxis.setLabelCount(10);
            yAxis.setAxisMinimum(0);
            yAxis.setAxisMaximum(15);


        } else {
            LineData lineData = lineChart.getLineData();
            ILineDataSet iLineDataSet = lineData.getDataSetByIndex(0);
            if (iLineDataSet == null) {
                iLineDataSet = createLineSet();
                lineData.addDataSet(iLineDataSet);
            }
            if (iLineDataSet.getEntryCount() > 50) {
                iLineDataSet.removeFirst();
            }

            iLineDataSet.addEntry(new Entry(time, value));
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.moveViewToX(time);
        }

    }

    private static LineDataSet createLineSet() {

        LineDataSet lineDataSet = new LineDataSet(null, "LineChart");
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighLightColor(Color.parseColor("#FF0000"));
        return lineDataSet;
    }


    //BarMPChart

    public static void barMpChart(BarChart barChart, int time, float value) {

        BarData barData = barChart.getData();
        if (barData == null) {
            barData = new BarData();
            barChart.setData(barData);

            barChart.setTouchEnabled(true);

            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(12);
            xAxis.setGranularity(2);
            xAxis.setTextColor(Color.RED);
            xAxis.setGridColor(Color.RED);
            xAxis.setEnabled(true);

            YAxis yAxis = barChart.getAxisLeft();
            yAxis.setTextColor(Color.RED);
            yAxis.setGridColor(Color.RED);
            yAxis.setLabelCount(10);
            yAxis.setAxisMinimum(0);
            yAxis.setAxisMaximum(15);

        } else {
            IBarDataSet iBarDataSet = barData.getDataSetByIndex(0);
            if (iBarDataSet == null) {
                iBarDataSet = createBarSet();
                barData.addDataSet(iBarDataSet);
            }
            iBarDataSet.addEntry(new BarEntry(time, value));
            barData.notifyDataChanged();
            barChart.notifyDataSetChanged();
            barChart.moveViewToX(time);
        }
    }

    private static BarDataSet createBarSet() {

        List<BarEntry> barEntryList = new ArrayList<>();
        BarDataSet barDataSet = new BarDataSet(barEntryList, "BarChart");
        barDataSet.setHighlightEnabled(true);
        barDataSet.setHighLightColor(Color.parseColor("#FF0000"));
        return barDataSet;
    }


    //PieMPChart

    public static void pieMpChart(PieChart pieChart, String name, float value) {

        PieData pieData = pieChart.getData();
        IPieDataSet iPieDataSet;
        if (pieData == null) {
            pieData = new PieData();
            iPieDataSet = createPieSet();
            pieData.setDataSet(iPieDataSet);
            pieChart.setData(pieData);

            pieChart.setUsePercentValues(true);
            pieChart.setExtraOffsets(5, 10, 5, 5);
            pieChart.setHoleRadius(58f);
            pieChart.setTransparentCircleRadius(61f);
        } else {
            iPieDataSet = pieData.getDataSetByIndex(0);
            iPieDataSet.addEntry(new PieEntry(value, name));
            pieData.notifyDataChanged();
            pieChart.notifyDataSetChanged();
        }
    }


    private static PieDataSet createPieSet() {

        PieDataSet pieDataSet = new PieDataSet(null, "PieChart");
        return pieDataSet;
    }


}
