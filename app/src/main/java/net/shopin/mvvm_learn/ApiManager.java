package net.shopin.mvvm_learn;

import net.shopin.mvvm_learn.dto.DoubanResult;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by zcs on 2019/2/22.
 */
public class ApiManager {

    private RetrofitHelper retrofitHelper;

    public ApiManager(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    public Flowable<DoubanResult> getNetMovie(int start, int count) {
        return retrofitHelper.getDoubanMovies(start, count);
    }


}
