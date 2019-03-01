package net.shopin.mvvm_learn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        MovieDetailFrag detailFrag = new MovieDetailFrag();
        detailFrag.setArguments(intent.getExtras());
        if (detailFrag.isAdded()) {
            getSupportFragmentManager().beginTransaction().show(detailFrag).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.movie_detail_container, detailFrag).commit();
        }

    }

    public static void start(Context context, Bundle bundle) {
        Intent starter = new Intent(context, MovieDetailActivity.class);
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

}
