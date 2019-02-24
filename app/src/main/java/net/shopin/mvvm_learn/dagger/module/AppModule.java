package net.shopin.mvvm_learn.dagger.module;

import net.shopin.mvvm_learn.ApiManager;
import net.shopin.mvvm_learn.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zcs on 2019/2/24.
 */

@Module
public class AppModule {

    @Provides
    public ApiManager provideApiManager(RetrofitHelper retrofitHelper) {
        return new ApiManager(retrofitHelper);
    }
}
