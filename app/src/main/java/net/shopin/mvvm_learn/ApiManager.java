package net.shopin.mvvm_learn;

import net.shopin.mvvm_learn.api.MovieApi;
import net.shopin.mvvm_learn.dto.DoubanResult;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.Flowable;

/**
 * Created by zcs on 2019/2/22.
 */
public class ApiManager {

    @Inject
    public RetrofitHelper retrofitHelper;

    @Inject
    public ApiManager() {
    }

    public  Flowable<DoubanResult> getNetMovie(int start, int count) {
        return retrofitHelper.getDoubanMovies(start, count);
    }


}
