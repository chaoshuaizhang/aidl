package net.shopin.mvvm_learn.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zcs on 2019/2/21.
 */

public abstract class BaseViewHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public VDB mViewDataBinding;

    public BaseViewHolder(VDB vdb, View itemView) {
        super(itemView);
        mViewDataBinding = vdb;
    }

}
