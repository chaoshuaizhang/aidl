package net.shopin.mvvm_learn;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zcs on 2019/2/16.
 * 多数据源融合成了一个数据仓库
 */
public class MainRepository {

    String TAG = "MainRepository";
    private static MainRepository INSTANCE;
    //单一数据源
    private MainModel mainModel;

    public MainRepository(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public static MainRepository getInstance(MainModel mainModel) {
        if (INSTANCE == null) {
            INSTANCE = new MainRepository(mainModel);
        }
        return INSTANCE;
    }

    public void getNetMovie(int start, int count, final MovieCallback callback) {
        Log.d(TAG, "getNetMovie: Repository 开始请求");
        mainModel.getNetMovie(start, count, callback)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<DoubanResult>() {
                    @Override
                    public void onNext(DoubanResult doubanResult) {
                        Log.d(TAG, "onNext: 请求结束");
                        callback.getMovies(doubanResult.getSubjects());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: 请求完成");
                    }
                })
        ;
    }

    public void updateMovie() {
        mainModel.updateMovie();
    }

}
