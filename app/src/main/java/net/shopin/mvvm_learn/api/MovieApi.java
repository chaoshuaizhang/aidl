package net.shopin.mvvm_learn.api;

import net.shopin.mvvm_learn.dto.DoubanResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zcs on 2019/2/16.
 */

public interface MovieApi {

    String BASE_URL = "https://api.douban.com/v2/movie/";

    @GET("top250")
    Flowable<DoubanResult> getDoubanMovies(@Query("start") int start, @Query("count") int count);
}
