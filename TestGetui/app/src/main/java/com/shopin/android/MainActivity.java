package com.shopin.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.igexin.sdk.PushManager;

import com.shopin.android.pushservice.MyGetuiIntentService;
import com.shopin.android.pushservice.MyGetuiService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushManager.getInstance().initialize(this.getApplicationContext(), MyGetuiService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), MyGetuiIntentService.class);
    }

    public void btnClick(View view) {
        MainActivity.start(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

}
