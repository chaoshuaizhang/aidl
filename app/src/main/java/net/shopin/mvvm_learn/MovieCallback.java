package net.shopin.mvvm_learn;

import java.util.List;

/**
 * Created by zcs on 2019/2/16.
 */

public interface MovieCallback {
    void getMovies(List<DoubanResult.MovieDTO> subjects);
}
