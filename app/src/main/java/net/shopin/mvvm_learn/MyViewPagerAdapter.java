package net.shopin.mvvm_learn;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.dto.Cast;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {

    private List<Cast> casts;
    private Context mContext;
    private List<View> views = new ArrayList<>();

    public MyViewPagerAdapter(List<Cast> casts, Context context) {
        this.mContext = context;
        this.casts = casts;
    }

    @Override
    public int getCount() {
        return casts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // FIXME: 2019/3/1/001 这一块有点坑，root最开始写的是container，然后就发生了栈溢出
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_item_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_actor);
        ImageView img = (ImageView) view.findViewById(R.id.img_actor);
        tv.setText(casts.get(position).getName());
        Glide.with(mContext)
                .load(casts.get(position).getAvatars().getLarge())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img);
        Logger.i(casts.get(position).toString());
        container.addView(view);
        views.add(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

}
