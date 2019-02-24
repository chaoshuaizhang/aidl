package net.shopin.mvvm_learn.dagger.module;

import net.shopin.mvvm_learn.ApiManager;
import net.shopin.mvvm_learn.api.MovieApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zcs on 2019/2/22.
 * 提供网络请求需要的类
 */
@Module
public class HttpModule {

    public HttpModule() {
    }

    @Provides
    public Retrofit.Builder provideBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    public OkHttpClient.Builder provideOKhttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    public Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient.Builder okBuilder) {
        return builder
                .client(okBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MovieApi.BASE_URL)
                .build();
    }

    /**
     * @return 刚开始想的是：直接返回一个movieApi，
     * 这个api在HttpModule的构造方法里初始化，但是参考后
     * 发现可以直接retrofit.create()-->返回的仍旧是MovieApi.class
     */
    @Provides
    public MovieApi provideMovieApi(Retrofit retrofit) {
        return retrofit.create(MovieApi.class);
    }

//    @Provides
//    public ApiManager provideApiManager() {
//        return new ApiManager();
//    }


}