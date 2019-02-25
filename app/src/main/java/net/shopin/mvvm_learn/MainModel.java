package net.shopin.mvvm_learn;

import android.util.Log;

import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.dto.DoubanResult;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import io.reactivex.Flowable;

/**
 * Created by zcs on 2019/2/15.
 */
public class MainModel {

    @Inject
    public MainModel() {
        Logger.t(App.TAG).i("MainModel = 初始化");
    }

    public Flowable<DoubanResult> getNetMovie(int start, int count) {
        if(App.getAppComponent().getApiManager() == null){
            Log.d("MainModelZcsTest", "getNetMovie: ");
        }
        return App.getAppComponent().getApiManager().getNetMovie(start, count);
    }

    public void updateMovie() {

    }
}
