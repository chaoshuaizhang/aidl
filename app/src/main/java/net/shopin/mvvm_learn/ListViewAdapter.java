package net.shopin.mvvm_learn;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.shopin.mvvm_learn.base.BaseAdapter;
import net.shopin.mvvm_learn.base.BaseViewHolder;
import net.shopin.mvvm_learn.databinding.MovieItemBinding;
import net.shopin.mvvm_learn.dto.MovieDTO;

import java.util.List;

/**
 * Created by zcs on 2019/2/17.
 */
public class ListViewAdapter extends BaseAdapter<MovieItemBinding> {

    private ObservableField<List<MovieDTO>> movieDTOs;
    private IOnItemClickListener itemClickListener;

    public ListViewAdapter(ObservableField<List<MovieDTO>> m,IOnItemClickListener itemClickListener) {
        movieDTOs = m;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public BaseViewHolder<MovieItemBinding> onCreateOwnViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<MovieItemBinding>(mViewDataBinding, mViewDataBinding.cardView) { };
    }

    @Override
    public int getlayoutId() {
        return R.layout.movie_item;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<MovieItemBinding> holder, int position) {
        // TODO 2019/2/21 给MovieItemBinding的MovieDTO设置值
        holder.mViewDataBinding.setMovieDto(movieDTOs.get().get(position));
        holder.mViewDataBinding.setItemCLick(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movieDTOs.get().size();
    }

    /**
     * 加载ImageView图片专用
     *
     * @param imageView
     * @param url
     */
    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.cover)
                .error(R.drawable.cover)
                .into(imageView);

    }

    /**
     * 更新recyclerview显示
     *
     * @param recyclerView
     * @param notify
     */
    @BindingAdapter({"app:mynotify"})
    public static void loadNotify(RecyclerView recyclerView, boolean notify) {
        Log.d("MainViewModel", "loadNotify: 开始更新了   " + notify);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public interface IOnItemClickListener {
        void onItemClick(View view, MovieDTO movieDTO);
    }

}
