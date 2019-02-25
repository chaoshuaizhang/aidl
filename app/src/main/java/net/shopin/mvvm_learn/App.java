package net.shopin.mvvm_learn;

import android.app.Application;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.dagger.component.AppComponent;
import net.shopin.mvvm_learn.dagger.component.DaggerAppComponent;
import net.shopin.mvvm_learn.dagger.module.HttpModule;

import javax.inject.Inject;

/**
 * Created by zcs on 2019/2/24.
 */

public class App extends Application {

    private static AppComponent appComponent;
    public static String TAG = "ApplicationLoggerTest";

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(App.getAppComponent().getAndroidLogAdapter());
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                    .builder()
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
