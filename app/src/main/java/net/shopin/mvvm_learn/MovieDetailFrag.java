package net.shopin.mvvm_learn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import net.shopin.mvvm_learn.dto.MovieDTO;

import java.io.Serializable;

/**
 * Created by zcs on 2019/2/25.
 */

public class MovieDetailFrag extends Fragment {

    String TAG = getClass().getSimpleName();
    private MyViewPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_movie_detail, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        Bundle bundle = getArguments();
        MovieDTO movieDTO = (MovieDTO) bundle.getSerializable("movieDTO");
        Toast.makeText(getActivity(), movieDTO.getTitle(), Toast.LENGTH_SHORT).show();
        pagerAdapter = new MyViewPagerAdapter(movieDTO.getCasts(), getActivity());
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
    }
}