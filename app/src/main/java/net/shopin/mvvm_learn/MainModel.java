package net.shopin.mvvm_learn;

import net.shopin.mvvm_learn.dto.DoubanResult;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.Flowable;

/**
 * Created by zcs on 2019/2/15.
 */

@Module
public class MainModel {

    private ApiManager apiManager;

    @Inject
    public MainModel(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public Flowable<DoubanResult> getNetMovie(int start, int count) {
        return apiManager.getNetMovie(start, count);
    }

    public void updateMovie() {

    }
}
