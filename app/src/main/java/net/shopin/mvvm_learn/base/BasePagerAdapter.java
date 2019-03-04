package net.shopin.mvvm_learn.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BasePagerAdapter<VDB extends ViewDataBinding, T> extends PagerAdapter {

    protected VDB vdb;
    protected List<T> list;
    protected Context mContext;

    public BasePagerAdapter(List<T> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
