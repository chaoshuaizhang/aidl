package net.shopin.mvvm_learn;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.base.BasePagerAdapter;
import net.shopin.mvvm_learn.databinding.ViewPagerItemBinding;
import net.shopin.mvvm_learn.dto.Cast;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends BasePagerAdapter<ViewPagerItemBinding, Cast> {

    private List<View> views = new ArrayList<>();

    public MyViewPagerAdapter(List<Cast> list, Context mContext) {
        super(list, mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Logger.t("日志日志").i(list.get(position).getName());
        // FIXME: 2019/3/1/001 这一块有点坑，root最开始写的是container，然后就发生了栈溢出
//        View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_item_layout, null);
        vdb = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.viewpager_item_layout, null, false);
        vdb.setCast(list.get(position));
        container.addView(vdb.getRoot());
        views.add(vdb.getRoot());
        return vdb.getRoot();
    }

    @BindingAdapter({"app:largeImgUrl"})
    public static void loadImagePic(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(view);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

}
