package net.shopin.mvvm_learn;

import net.shopin.mvvm_learn.api.MovieApi;
import net.shopin.mvvm_learn.dto.DoubanResult;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by zcs on 2019/2/22.
 */

public class ApiManager {

    private MovieApi movieApi;

    @Inject
    public ApiManager(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    public  Flowable<DoubanResult> getNetMovie(int start, int count) {
        return movieApi.getDoubanMovies(start, count);
    }


}
