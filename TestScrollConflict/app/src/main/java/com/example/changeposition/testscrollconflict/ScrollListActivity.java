package com.example.changeposition.testscrollconflict;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScrollListActivity extends AppCompatActivity{

    private MyListView listView;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        listView = findViewById(R.id.list_view);
        layout = findViewById(R.id.container);
        listView.setMyParent(layout);
        init();
    }

    void init() {
        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final List<String> datas = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            TextView textView = new TextView(this);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(235, 100);
            textView.setLayoutParams(layoutParams);
            textView.setText("" + i);
            textView.setTextSize(25);
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
        }
        for (int i = 0; i < 125; i++) {
            datas.add(new Random().nextInt(100000000) + "  " + i);
//            TextView textView = new TextView(this);
//            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 45);
//            textView.setLayoutParams(layoutParams);
//            textView.setText("" + i);
//            textView.setTextSize(25);
//            textView.setGravity(Gravity.CENTER);
//            linearLayout.addView(textView);
        }
        layout.addView(linearLayout);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public Object getItem(int position) {
                return datas.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(ScrollListActivity.this).inflate(R.layout.item_layout, null);
                }
                TextView tv = convertView.findViewById(R.id.item_tv);
                tv.setText(datas.get(position));
                return convertView;
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ScrollListActivity.class);
        context.startActivity(starter);
    }



}
