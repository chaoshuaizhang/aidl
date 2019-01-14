package net.shopin.testactivitylaunch.singletask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.shopin.testactivitylaunch.R;
import net.shopin.testactivitylaunch.singletop.SingleTopActivity;
import net.shopin.testactivitylaunch.standard.StandardActivity;

public class SingleTaskActivity extends AppCompatActivity {

    private String TAG = "SingleTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_current_activity:
                Intent intent = new Intent(this, SingleTaskActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
}
