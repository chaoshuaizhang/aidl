package net.shopin.mvvm_learn.module;

import android.util.Log;

import net.shopin.mvvm_learn.ApiManager;
import net.shopin.mvvm_learn.Apione;
import net.shopin.mvvm_learn.RetrofitHelper;
import net.shopin.mvvm_learn.api.MovieApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by zcs on 2019/2/22.
 * 提供外部使用的工具类
 */
@Module
public class ActivityModule {

    private String TAG = "AppModule";

    public ActivityModule() {

    }

    @Provides
    RetrofitHelper provideRetrofitHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(MovieApi.BASE_URL).client(new OkHttpClient()).build();
    }

    @Provides
    MovieApi provideMovieApi(Retrofit retrofit) {
        return retrofit.create(MovieApi.class);
    }

    @Provides
    ApiManager provideApiManager(MovieApi movieApi) {
        if (movieApi != null) {
            Log.d(TAG, "provideApiManager: != null");
        } else {
            Log.d(TAG, "provideApiManager: == null");
        }
        return new ApiManager(movieApi);
    }
}
