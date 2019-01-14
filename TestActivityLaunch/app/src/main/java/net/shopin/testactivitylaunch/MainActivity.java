package net.shopin.testactivitylaunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.shopin.testactivitylaunch.singleinstance.SingleInstanceActivity;
import net.shopin.testactivitylaunch.singletask.SingleTaskActivity;
import net.shopin.testactivitylaunch.singletop.SingleTopActivity;
import net.shopin.testactivitylaunch.standard.StandardActivity;
import net.shopin.testactivitylaunch.standard.Standard_AffinityActivity;
import net.shopin.testactivitylaunch.standard.Standard_NewTaskActivity;
import net.shopin.testactivitylaunch.standard.Standard_NewTask_AffinityActivity;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_standard:
                intent = new Intent(this, StandardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
            case R.id.btn_standard_task:
                intent = new Intent(this, Standard_NewTaskActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
            case R.id.btn_standard_affinity:
                intent = new Intent(this, Standard_AffinityActivity.class);
                break;
            case R.id.btn_standard_newtask_affinity:
                intent = new Intent(this, Standard_NewTask_AffinityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
            case R.id.btn_application_context_start:
                App.getAppInstance().start();
            case R.id.btn_singletop:
                intent = new Intent(this, SingleTopActivity.class);
                break;
            case R.id.btn_singletask:
                intent = new Intent(this, SingleTaskActivity.class);
                break;
            case R.id.btn_singleinstance:
                intent = new Intent(this, SingleInstanceActivity.class);
                break;
        }
        startActivity(intent);
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
