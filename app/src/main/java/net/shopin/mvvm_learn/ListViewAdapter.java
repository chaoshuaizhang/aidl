package net.shopin.mvvm_learn;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.shopin.mvvm_learn.databinding.MovieItemBinding;

import java.util.List;

/**
 * Created by zcs on 2019/2/17.
 */

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.VH> {

    String TAG = "ListViewAdapter";
    private Context mContext;
    private MainViewModel mainViewModel;

    public ListViewAdapter(Context mContext, MainViewModel viewModel) {
        this.mContext = mContext;
        mainViewModel = viewModel;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(mContext), R.layout.movie_item, parent, false);
        return new VH(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.itemBinding.setMovieDto(mainViewModel.movieDTO.get().get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return mainViewModel.movieDTO.get().size();
    }

    class VH extends RecyclerView.ViewHolder {

        private MovieItemBinding itemBinding;

        public VH(MovieItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }

}
