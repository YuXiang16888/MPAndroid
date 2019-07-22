package com.example.mpandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mListChartSelector;
    private ArrayAdapter mArrayAdapter;
    private String[] mChartName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListChartSelector = findViewById(R.id.list_chart_selector);
        mChartName = getResources().getStringArray(R.array.chartgroup);
        mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,mChartName);
        mListChartSelector.setAdapter(mArrayAdapter);
        mListChartSelector.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener  = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(MainActivity.this,ChartActivity.class);
            intent.putExtra("ChartMode",mChartName[i]);
            startActivity(intent);
        }
    };
}
