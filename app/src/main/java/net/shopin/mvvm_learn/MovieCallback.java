package net.shopin.mvvm_learn;

import net.shopin.mvvm_learn.dto.DoubanResult;
import net.shopin.mvvm_learn.dto.MovieDTO;

import java.util.List;

/**
 * Created by zcs on 2019/2/16.
 */

public interface MovieCallback {
    void getMovies(List<MovieDTO> subjects);
}
