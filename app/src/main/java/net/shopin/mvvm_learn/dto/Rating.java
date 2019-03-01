package net.shopin.mvvm_learn.dto;

import java.io.Serializable;

/**
 * Created by zcs on 2019/2/19.
 */

public class Rating implements Serializable {
    float average;

    public void setAverage(float average) {
        this.average = average;
    }

    public float getAverage() {
        return average;
    }
}
