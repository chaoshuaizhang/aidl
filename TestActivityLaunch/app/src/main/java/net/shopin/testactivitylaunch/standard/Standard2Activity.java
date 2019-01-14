package net.shopin.testactivitylaunch.standard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.shopin.testactivitylaunch.R;

public class Standard2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard2);
    }

    public void btnClick(View view) {

        Intent intent = new Intent(this, Standard_NewTask_AffinityActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
