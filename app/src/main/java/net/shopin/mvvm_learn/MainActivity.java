package net.shopin.mvvm_learn;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import net.shopin.mvvm_learn.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    private ActivityMainBinding viewDataBinding;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainViewModel();
        viewModel.getNetMovies(new Random().nextInt(50) + 1, new Random().nextInt(200) + 1);
        //View 和 VM建立关联
        viewDataBinding.setMainViewModel(viewModel);
        listViewAdapter = new ListViewAdapter(this, viewModel);
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        viewDataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.recyclerView.setAdapter(listViewAdapter);
    }
}
