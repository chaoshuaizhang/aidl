package net.shopin.myaidlserver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SERVER_ERROR_LOG";
    private IBookManager bookManager;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //main线程
            Log.e(TAG, "onServiceConnected  Thread : " + Thread.currentThread().getName());
            try {
                //打印出的确实是：net.shopin.myaidlserver.IBookManager
                Log.e(TAG, "getInterfaceDescriptor: " + service.getInterfaceDescriptor());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            IInterface iInterface = service.queryLocalInterface("net.shopin.myaidlserver.IBookManager");
            if (iInterface != null) {
                if (iInterface instanceof net.shopin.myaidlserver.IBookManager) {
                    Log.e(TAG, "server端：instanceof");
                } else {
                    Log.e(TAG, "server端：!instanceof");
                }
            } else {
                Log.e(TAG, "server端：iInterface == null");
            }
            bookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: " + name.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("net.shopin.aidlservice");
        intent.setPackage("net.shopin.myaidlserver");
//        //与服务端建立连接
//        //这边有个小问题：在aip>21时，上述的intent不仅要加action，还要加package，
//        //具体可以看下源码，笔记会分析。
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        findViewById(R.id.op).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = new Random().nextInt(100);
                Book b = new Book(i, "name" + i);
                try {
                    /*
                    * addBook是在main线程中调用，但是实际的执行是在服务端进程中的Binder线程中执行
                    * 此时：main线程会挂起，直到Binder执行完毕
                    * */
                    long t = System.currentTimeMillis();
                    bookManager.addBook(b);
                    Toast.makeText(MainActivity.this, System.currentTimeMillis() - t + "", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onClick: " + Process.myPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(MainActivity.this, bookManager.getBookList().size() + "", Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
