package net.shopin.testactivitylaunch.standard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.shopin.testactivitylaunch.R;

public class StandardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
    }

    public void btnClick(View view) {
        Intent intent = new Intent(this, StandardActivity.class);
        startActivity(intent);
    }

}
