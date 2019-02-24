package net.shopin.mvvm_learn;

import android.util.Log;

import net.shopin.mvvm_learn.dto.DoubanResult;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.Flowable;

/**
 * Created by zcs on 2019/2/15.
 */

public class MainModel {

    @Inject
    public ApiManager mApiManager;

    @Inject
    public MainModel() {
    }

    public Flowable<DoubanResult> getNetMovie(int start, int count) {
        if(mApiManager == null){
            Log.d("MainModelZcsTest", "getNetMovie: ");
        }
        return mApiManager.getNetMovie(start, count);
    }

    public void updateMovie() {

    }
}
