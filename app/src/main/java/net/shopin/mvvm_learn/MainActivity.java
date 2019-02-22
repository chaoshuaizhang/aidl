package net.shopin.mvvm_learn;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import net.shopin.mvvm_learn.base.BaseActivity;
import net.shopin.mvvm_learn.component.DaggerActivityComponent;
import net.shopin.mvvm_learn.databinding.ActivityMainBinding;
import net.shopin.mvvm_learn.module.ActivityModule;

import java.util.Random;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> implements SwipeRefreshLayout.OnRefreshListener {

    private ListViewAdapter listViewAdapter;
    @Inject
    public MainViewModel mainViewModel;

    @Override
    protected void init() {
        DaggerActivityComponent.builder().build().inject(this);
        //View 和 VM建立关联
        mViewDataBinding.setMainViewModel(mainViewModel);
        mViewDataBinding.getMainViewModel().movieVisible.set(View.GONE);
        mViewDataBinding.getMainViewModel().refresh.set(View.VISIBLE);
        //mViewDataBinding.getMainViewModel().getNetMovies(10, 15);
        //ListView适配器
        listViewAdapter = new ListViewAdapter(mViewDataBinding.getMainViewModel().movieDTO);
        mViewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mViewDataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mViewDataBinding.recyclerView.setAdapter(listViewAdapter);
        mViewDataBinding.swiperefreshLayout.setOnRefreshListener(this);
    }

    public void btnClick(View view) {
        //1. 数据源的改变能通知View的改变
        mViewDataBinding.getMainViewModel().movieDTO.get().get(0).setTitle("标题：" + new Random().nextInt(100));
        mViewDataBinding.getMainViewModel().movieDTO.get().get(0).setYear("年份：" + new Random().nextInt(1000));
        Toast.makeText(this, mViewDataBinding.getMainViewModel().movieDTO.get().get(1).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        int i = new Random().nextInt(10) + new Random().nextInt(10);
        mViewDataBinding.getMainViewModel().getNetMovies(i, i);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule();
    }

}
