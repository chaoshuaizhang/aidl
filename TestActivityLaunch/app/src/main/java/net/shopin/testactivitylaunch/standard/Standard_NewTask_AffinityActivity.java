package net.shopin.testactivitylaunch.standard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.shopin.testactivitylaunch.R;

public class Standard_NewTask_AffinityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard__new_task__affinity);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("onNewIntent", "onNewIntent: =====================");
    }

    public void btnClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_start_current_activity:
                intent = new Intent(this, Standard_NewTask_AffinityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.btn_start_new_standard_activity:
                intent = new Intent(this, StandardActivity.class);
                startActivity(intent);
                break;
        }
    }

}
