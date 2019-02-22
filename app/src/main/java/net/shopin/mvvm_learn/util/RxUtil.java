package net.shopin.mvvm_learn.util;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zcs on 2019/2/22.
 */

public class RxUtil {
    public static <U> FlowableTransformer<U, U> r() {
        return new FlowableTransformer<U, U>() {
            @Override
            public Publisher<U> apply(@NonNull Flowable<U> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
