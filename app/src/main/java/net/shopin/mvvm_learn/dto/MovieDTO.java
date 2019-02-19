package net.shopin.mvvm_learn.dto;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import net.shopin.mvvm_learn.BR;

import java.util.List;

/**
 * Created by zcs on 2019/2/19.
 */
public class MovieDTO extends BaseObservable {
    private String id;
    private String alt;
    //对于ObservableField<String> year这种方式是无法解析的，想想也
    //是：json对应的是String类型，但是现在是ObservableField类型
    //public ObservableField<String> year = new ObservableField<>();
    public String year;
    public String title;
    private String original_title;
    private List<String> genres;
    private List<Cast> casts;
    private List<Cast> directors;
    private Avatars images;
    private Rating rating;

    public Rating getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setYear(String year) {
        this.year = year;
        notifyPropertyChanged(BR.year);
    }

    @Bindable
    public String getYear() {
        return year;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Cast> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Cast> directors) {
        this.directors = directors;
    }

    public Avatars getImages() {
        return images;
    }

    public void setImages(Avatars images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id='" + id + '\'' +
                ", alt='" + alt + '\'' +
                ", year='" + year + '\'' +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                ", images=" + images +
                ", rating=" + rating +
                '}';
    }
}
