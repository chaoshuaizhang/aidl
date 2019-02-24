package net.shopin.mvvm_learn.dagger.component;

import net.shopin.mvvm_learn.ApiManager;
import net.shopin.mvvm_learn.dagger.module.AppModule;
import net.shopin.mvvm_learn.dagger.module.HttpModule;

import dagger.Component;

/**
 * Created by zcs on 2019/2/24.
 */
@Component(modules = {HttpModule.class, AppModule.class})
public interface AppComponent {

    /*
    * ApiManager所需要的类，不一定要ApiManager提供，
    * 找一个Module提供就行
    * 需要的类：
    * MovieApi
    * */
    ApiManager getApiManager();
}
