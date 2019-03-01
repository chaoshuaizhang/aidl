package net.shopin.mvvm_learn.dto;

import java.io.Serializable;

/**
 * Created by zcs on 2019/2/19.
 */

public class Cast implements Serializable {
    private String id;
    private String name;
    private String alt;
    private Avatars avatars;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    @Override
    public String toString() {
        return "cast.id=" + id + " cast.name=" + name + " | ";
    }
}
