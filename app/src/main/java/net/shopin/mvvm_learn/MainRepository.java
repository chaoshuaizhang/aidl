package net.shopin.mvvm_learn;

import android.util.Log;

import net.shopin.mvvm_learn.base.BaseRepository;
import net.shopin.mvvm_learn.base.BaseResourceSubscriber;
import net.shopin.mvvm_learn.dto.DoubanResult;
import net.shopin.mvvm_learn.util.RxUtil;

/**
 * Created by zcs on 2019/2/16.
 * 多数据源融合成了一个数据仓库
 */
public class MainRepository extends BaseRepository {

    String TAG = "MainRepository";
    private static MainRepository INSTANCE;
    //单一数据源
    private MainModel mainModel;

    public MainRepository(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public static MainRepository getInstance(MainModel mainModel) {
        if (INSTANCE == null) {
            INSTANCE = new MainRepository(mainModel);
        }
        return INSTANCE;
    }

    public void getNetMovie(int start, int count, final MovieCallback callback) {
        Log.d(TAG, "getNetMovie: Repository 开始请求");
        addSubscribe(mainModel.getNetMovie(start, count)
                .compose(RxUtil.<DoubanResult>r())
                .subscribeWith(new BaseResourceSubscriber<DoubanResult>() {
                    @Override
                    protected void handleData(DoubanResult data) {
                        Log.d(TAG, "onNext: 请求结束");
                        callback.getMovies(data.getSubjects());
                    }
                }));
    }

    public void updateMovie() {
        mainModel.updateMovie();
    }

}
