package net.shopin.mvvm_learn.base;

import android.util.Log;

import io.reactivex.subscribers.ResourceSubscriber;


/**
 * Created by zcs on 2019/2/22.
 */

public abstract class BaseResourceSubscriber<T> extends ResourceSubscriber<T> {

    private String TAG = getClass().getSimpleName();

    @Override
    public void onNext(T t) {
        handleData(t);
    }

    @Override
    public void onError(Throwable t) {
        //这个最好是全局控制错误信息，不需要每个接口请求来处理
        handleError(t.getMessage());
    }

    @Override
    public void onComplete() {
        //
    }

    protected abstract void handleData(T data);

    protected void handleError(String error) {
        Log.d(TAG, "handleError: " + error);
    }

}
