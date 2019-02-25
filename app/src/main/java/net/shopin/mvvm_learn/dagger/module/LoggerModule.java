package net.shopin.mvvm_learn.dagger.module;

import com.orhanobut.logger.AndroidLogAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zcs on 2019/2/25.
 */
@Module
public class LoggerModule {

    @Provides
    public AndroidLogAdapter provideAndroidLogAdapter() {
        return new AndroidLogAdapter();
    }
}
