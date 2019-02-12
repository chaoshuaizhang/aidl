package net.shopin.testviewevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnClick(View view) {
        if (view.getId() == R.id._main_) {
            MainActivity.start(this);
        } else if (view.getId() == R.id._slide_) {
            ViewSlideActivity.start(this);
        } else if (view.getId() == R.id._flex_slide_) {
            FlexSlideActivity.start(this);
        }
    }

}
