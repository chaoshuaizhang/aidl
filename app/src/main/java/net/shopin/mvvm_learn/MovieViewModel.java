package net.shopin.mvvm_learn;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zcs on 2019/2/17.
 */

public class MovieViewModel extends BaseObservable {
    private DoubanResult.MovieDTO movieDTO;

    public MovieViewModel(DoubanResult.MovieDTO movieDTO) {
        this.movieDTO = movieDTO;
    }

    public String getCoverUrl() {
        return movieDTO.getImages().getSmall();
    }

    public String getTitle() {
        return movieDTO.getTitle();
    }

    public float getRating() {
        return movieDTO.getRating().getAverage();
    }

    public String getRatingText(){
        return String.valueOf(movieDTO.getRating().getAverage());
    }

    public String getYear() {
        return movieDTO.getYear();
    }

    public String getMovieType() {
        StringBuilder builder = new StringBuilder();
        for (String s : movieDTO.getGenres()) {
            builder.append(s + " ");
        }
        return builder.toString();
    }

    public String getImageUrl() {
        return movieDTO.getImages().getSmall();
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.cover)
                .error(R.drawable.cover)
                .into(imageView);

    }
    
}
