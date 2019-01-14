package net.shopin.testactivitylaunch.singletop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.shopin.testactivitylaunch.R;
import net.shopin.testactivitylaunch.standard.Standard_NewTask_AffinityActivity;

public class SingleTopActivity extends AppCompatActivity {

    private String TAG = "SingleTopActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
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

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_current_activity:
                Intent intent = new Intent(this, SingleTopActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

}
