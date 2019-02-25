package net.shopin.mvvm_learn.dagger.module;

import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.ApiManager;
import net.shopin.mvvm_learn.App;
import net.shopin.mvvm_learn.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zcs on 2019/2/24.
 */

@Module
public class AppModule {

    public AppModule() {
        Logger.t(App.TAG).i("AppModule = 初始化");
    }

    @Provides
    public ApiManager provideApiManager(RetrofitHelper retrofitHelper) {
        return new ApiManager(retrofitHelper);
    }
}
