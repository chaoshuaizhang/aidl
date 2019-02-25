package net.shopin.mvvm_learn.dagger.module;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zcs on 2019/2/25.
 */
@Module
public class LoggerModule {

    public LoggerModule() {
        Logger.t(App.TAG).i("LoggerModule = 初始化");
    }

    @Provides
    public AndroidLogAdapter provideAndroidLogAdapter() {
        return new AndroidLogAdapter();
    }
}
