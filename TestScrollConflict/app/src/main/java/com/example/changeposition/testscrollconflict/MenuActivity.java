package com.example.changeposition.testscrollconflict;

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
        switch (view.getId()) {
            case R.id.btn_scroll_list:
                ScrollListActivity.start(this);
                break;
        }
    }

}
