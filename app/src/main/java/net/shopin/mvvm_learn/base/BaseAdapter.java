package net.shopin.mvvm_learn.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.shopin.mvvm_learn.R;

/**
 * Created by zcs on 2019/2/21.
 */

public abstract class BaseAdapter<VDB extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<VDB>> {

    protected String TAG = getClass().getSimpleName();

    protected VDB mViewDataBinding;

    @Override
    public BaseViewHolder<VDB> onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewDataBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), getlayoutId(), parent, false);
        return onCreateOwnViewHolder(parent, viewType);
    }

    public abstract int getlayoutId();

    public abstract BaseViewHolder<VDB> onCreateOwnViewHolder(ViewGroup parent, int viewType);
}
