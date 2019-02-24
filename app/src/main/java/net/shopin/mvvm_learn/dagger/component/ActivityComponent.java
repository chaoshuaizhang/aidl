package net.shopin.mvvm_learn.dagger.component;

import net.shopin.mvvm_learn.MainActivity;
import net.shopin.mvvm_learn.dagger.module.ActivityModule;

import dagger.Component;

/**
 * Created by zcs on 2019/2/22.
 * 说的是每个
 */
//dependencies：把AppComponent中提供的一些对象,工具依赖进来，实现共用
@Component(modules = ActivityModule.class, dependencies = {AppComponent.class})
public interface ActivityComponent {

    void inject(MainActivity activity);


}
