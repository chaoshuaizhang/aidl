package com.shopin.android;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by zcs on 2019/2/11.
 */

public class App extends Application {

    private static MsgHandler handler;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(this).get();
        if (handler == null) {
            handler = new MsgHandler();
        }
    }

    public static void sendMsg(final Message msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg.what + " " + msg.obj, Toast.LENGTH_LONG).show();
            }
        });
    }

    class MsgHandler extends Handler {
    }

}
