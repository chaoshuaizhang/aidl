package net.shopin.mvvm_learn.dto;

import java.util.List;

/**
 * Created by zcs on 2019/2/16.
 */
public class DoubanResult {
    private List<MovieDTO> subjects;
    private int count;
    private int start;
    private int total;

    public List<MovieDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<MovieDTO> subjects) {
        this.subjects = subjects;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}