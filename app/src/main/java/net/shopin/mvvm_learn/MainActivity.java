package net.shopin.mvvm_learn;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.shopin.mvvm_learn.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    String TAG = "MainMaivityActivity";

    private ActivityMainBinding viewDataBinding;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //View 和 VM建立关联
        viewDataBinding.setMainViewModel(new MainViewModel());
        viewDataBinding.getMainViewModel().movieVisible.set(View.GONE);
        viewDataBinding.getMainViewModel().refresh.set(View.VISIBLE);
        viewDataBinding.getMainViewModel().getNetMovies(10, 15);
        //ListView适配器
        listViewAdapter = new ListViewAdapter(viewDataBinding.getMainViewModel(), viewDataBinding.getMainViewModel().movieDTO.get(), viewDataBinding.getMainViewModel().movieDTO);
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        viewDataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.recyclerView.setAdapter(listViewAdapter);
        viewDataBinding.swiperefreshLayout.setOnRefreshListener(this);
    }

    public void btnClick(View view) {
        //1. 数据源的改变能通知View的改变
        viewDataBinding.getMainViewModel().movieDTO.get().get(0).setTitle("标题：" + new Random().nextInt(100));
        viewDataBinding.getMainViewModel().movieDTO.get().get(0).setYear("年份：" + new Random().nextInt(1000));
        Log.d(TAG, "btnClick: " + viewDataBinding.getMainViewModel().movieDTO.get().get(1).getTitle());
        Log.d(TAG, "btnClick: Visible = " + viewDataBinding.getMainViewModel().movieVisible.get());
        //mainViewModel.movieVisible.set(viewDataBinding.swiperefreshLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        //viewDataBinding.swiperefreshLayout.setVisibility(viewDataBinding.swiperefreshLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        Log.d(TAG, "btnClick: Visible = " + viewDataBinding.getMainViewModel().movieVisible.get());
        Toast.makeText(this, viewDataBinding.getMainViewModel().movieDTO.get().get(1).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        int i = new Random().nextInt(10) + new Random().nextInt(10);
        viewDataBinding.getMainViewModel().getNetMovies(i, i);
    }


}
