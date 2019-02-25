package net.shopin.mvvm_learn.dagger.module;

import android.nfc.Tag;
import android.util.Log;

import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.ApiManager;
import net.shopin.mvvm_learn.App;
import net.shopin.mvvm_learn.RetrofitHelper;
import net.shopin.mvvm_learn.api.MovieApi;

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


    public ActivityModule(){
        Logger.t(App.TAG).i("ActivityModule = 初始化");
    }

}
