package net.shopin.mvvm_learn;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by zcs on 2019/2/15.
 */

public class MainViewModel extends BaseObservable {

    String TAG = "MainViewModel";

    //主内容
    public ObservableField<List<DoubanResult.MovieDTO>> movieDTO = new ObservableField<>();
    //主内容是否显示
    public ObservableField<Integer> movieVisible = new ObservableField<>();
    //是否正在刷新
    public ObservableField<Integer> refresh = new ObservableField<>();
    //是否显示错误信息
    public ObservableField<String> error = new ObservableField<>();

    private MainModel mainModel = new MainModel();

    private MainRepository mainRepository = MainRepository.getInstance(mainModel);

    public MainViewModel() {
        movieVisible.set(View.GONE);
        refresh.set(View.VISIBLE);
    }

    public void getNetMovies(int start, int count) {
        Log.d(TAG, "getNetMovies: 开始请求网络数据");
        mainRepository.getNetMovie(start, count, new MovieCallback() {
            @Override
            public void getMovies(List<DoubanResult.MovieDTO> subjects) {
                Log.d(TAG, "getMovies: " + (subjects == null ? "== null" : subjects.size()));
                //双向绑定-根据数据源的改变动态更新UI
                movieDTO.set(subjects);
                movieVisible.set(View.VISIBLE);
                refresh.set(View.GONE);
            }
        });
    }

}
