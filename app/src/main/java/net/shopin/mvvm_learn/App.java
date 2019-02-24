package net.shopin.mvvm_learn;

import android.app.Application;

import net.shopin.mvvm_learn.dagger.component.AppComponent;
import net.shopin.mvvm_learn.dagger.component.DaggerAppComponent;
import net.shopin.mvvm_learn.dagger.module.HttpModule;

/**
 * Created by zcs on 2019/2/24.
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
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
