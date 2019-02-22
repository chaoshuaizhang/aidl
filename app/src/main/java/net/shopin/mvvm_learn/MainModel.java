package net.shopin.mvvm_learn;

import net.shopin.mvvm_learn.dto.DoubanResult;

import io.reactivex.Flowable;

/**
 * Created by zcs on 2019/2/15.
 */

public class MainModel {

    private RetrofitHelper retrofitHelper;

    public MainModel() {
        retrofitHelper = new RetrofitHelper();
    }

    public Flowable<DoubanResult> getNetMovie(int start, int count) {
        return retrofitHelper.getDoubanMovies(start, count);
    }

    public void updateMovie() {

    }
}
