package net.shopin.mvvm_learn.dto;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import com.orhanobut.logger.Logger;

import java.io.Serializable;

/**
 * Created by zcs on 2019/2/19.
 */

public class Avatars extends BaseObservable implements Serializable {
    private String small;
    private String medium;
    private String large;

    @Bindable
    public String getLargeImgUrl() {
        return large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
