package net.shopin.mvvm_learn.dagger.component;

import net.shopin.mvvm_learn.MainActivity;
import net.shopin.mvvm_learn.dagger.module.ActivityModule;

import dagger.Component;

/**
 * Created by zcs on 2019/2/22.
 */
@Component(modules = ActivityModule.class, dependencies = {AppComponent.class})
public interface ActivityComponent {

    void inject(MainActivity activity);


}
