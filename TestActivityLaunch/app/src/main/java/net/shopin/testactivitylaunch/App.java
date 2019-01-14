package net.shopin.testactivitylaunch;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import net.shopin.testactivitylaunch.standard.StandardActivity;

/**
 * Created by zcs on 2019/1/13.
 */

public class App extends Application {

    public static App appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public static App getAppInstance() {
        return appInstance;
    }


    public void start() {
        //以下两种都是OK的
        //getApplicationContext().startActivity(new Intent(this, StandardActivity.class));
        startActivity(new Intent(this, StandardActivity.class));
    }

}














