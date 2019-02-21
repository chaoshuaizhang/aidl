package net.shopin.mvvm_learn;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.shopin.mvvm_learn.databinding.MovieItemBinding;
import net.shopin.mvvm_learn.dto.MovieDTO;

import java.util.List;

/**
 * Created by zcs on 2019/2/17.
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.VH> {

    String TAG = "ListViewAdapter";
    private MainViewModel mainViewModel;
    private List<MovieDTO> mList;
    ObservableField<List<MovieDTO>> m;

    public ListViewAdapter(MainViewModel viewModel, List<MovieDTO> list, ObservableField<List<MovieDTO>> m) {
        mainViewModel = viewModel;
        mList = list;
        this.m = m;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + parent.getContext().hashCode());
        MovieItemBinding movieItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new VH(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        // TODO 2019/2/21 给MovieItemBinding的MovieDTO设置值
        holder.itemBinding.setMovieDto(mainViewModel.movieDTO.get().get(position));
        holder.itemBinding.setItemCLick(new IOnItemClickListener() {
            @Override
            public void onItemClick(View view, MovieDTO movieDTO) {
                Log.d(TAG, "onItemClick: " + Thread.currentThread().getName() + "  " + movieDTO.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainViewModel.movieDTO.get().size();
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

    class VH extends RecyclerView.ViewHolder {

        // TODO 2019/2/21 MovieItemBinding需要接收一个数据源movieDto
        private MovieItemBinding itemBinding;

        public VH(MovieItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }

    public interface IOnItemClickListener {
        void onItemClick(View view, MovieDTO movieDTO);
    }

}
