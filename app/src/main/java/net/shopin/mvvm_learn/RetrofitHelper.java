package net.shopin.mvvm_learn;

import net.shopin.mvvm_learn.api.MovieApi;
import net.shopin.mvvm_learn.dto.DoubanResult;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zcs on 2019/2/16.
 */

public class RetrofitHelper {
    private MovieApi movieApi;

    @Inject
    public RetrofitHelper(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    public Flowable<DoubanResult> getDoubanMovies(int start, int count) {
        return movieApi.getDoubanMovies(start, count);
    }
}
