package net.shopin.mvvm_learn;

import java.util.concurrent.TimeUnit;

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
    private Retrofit retrofit;
    OkHttpClient.Builder builder;

    public RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MovieApi.BASE_URL)
                .build();
        movieApi = retrofit.create(MovieApi.class);
    }

    public Flowable<DoubanResult> getDoubanMovies(int start, int count) {
        return movieApi.getDoubanMovies(start, count);
    }
}
