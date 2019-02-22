package net.shopin.mvvm_learn;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import net.shopin.mvvm_learn.base.BaseViewModel;
import net.shopin.mvvm_learn.dto.MovieDTO;

import java.util.List;

/**
 * Created by zcs on 2019/2/15.
 */

public class MainViewModel extends BaseViewModel {

    static String TAG = "MainViewModel";

    //主内容
    public ObservableField<List<MovieDTO>> movieDTO = new ObservableField<>();
    //主内容是否显示
    public ObservableField<Integer> movieVisible = new ObservableField<>();
    //是否正在刷新
    public ObservableField<Integer> refresh = new ObservableField<>();
    //是否显示错误信息
    public ObservableField<String> error = new ObservableField<>();

    private MainModel mainModel = new MainModel();

    private MainRepository mainRepository = MainRepository.getInstance(mainModel);

    public boolean swipeRefresh;

    public MainViewModel() {
    }

    @Bindable
    public boolean isSwipeRefresh() {
        return swipeRefresh;
    }

    public void setSwipeRefresh(boolean swipeRefresh) {
        this.swipeRefresh = swipeRefresh;
        notifyPropertyChanged(BR.swipeRefresh);
    }

    public void getNetMovies(int start, int count) {
        Log.d(TAG, "getNetMovies: 开始请求网络数据");
        mainRepository.getNetMovie(start, count, new MovieCallback() {
            @Override
            public void getMovies(List<MovieDTO> subjects) {
                Log.d(TAG, "getMovies: " + (subjects == null ? "== null" : subjects.size()));
                //双向绑定-根据数据源的改变动态更新UI
                movieDTO.set(subjects);
                movieVisible.set(View.VISIBLE);
                refresh.set(View.GONE);
                Log.d(TAG, "getMovies: 请求结束了");
                //停止下拉刷新
                setSwipeRefresh(false);
                //更新数据源
                notifyPropertyChanged(BR.mynotify);
            }
        });
    }

    @Bindable
    public boolean getMynotify(){
        return true;
    }

    public void destroy(){
        mainRepository.unSubscribe();
    }
}
