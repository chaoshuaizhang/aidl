package net.shopin.mvvm_learn.dagger.component;

import net.shopin.mvvm_learn.MainActivity;
import net.shopin.mvvm_learn.MainViewModel;
import net.shopin.mvvm_learn.dagger.module.ActivityModule;
import net.shopin.mvvm_learn.dagger.scope.ActivityScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zcs on 2019/2/22.
 * 说的是每个
 */
//dependencies：把AppComponent中提供的一些对象,工具依赖进来，实现共用
//This @Singleton component cannot depend on scoped components,
//如果依赖的component中也使用了@singleton时, 被依赖的地方就不能使用了,
//于是自定义一个Scope: ActivityScope
@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = {AppComponent.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

}
