package net.shopin.myaidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.shopin.myaidlserver.Book;
import net.shopin.myaidlserver.BookListener;
import net.shopin.myaidlserver.IBookManager;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CLIENT_ERROR_LOG";

    private IBookManager bookManager;

    private BookListener listener = new BookListener.Stub() {

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Log.e(TAG, "client   onTransact: ------------------" + code);
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void hasBook() throws RemoteException {
            Toast.makeText(MainActivity.this, "有书来了" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void noBook() throws RemoteException {
            Toast.makeText(MainActivity.this, "取消注册" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
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
        findViewById(R.id.reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setAction("android.intent.action.MAIN");
                ComponentName cn = new ComponentName("net.shopin.myaidlserver", "net.shopin.myaidlserver.MainActivity");
                intent.setComponent(cn);
                startActivity(intent);
                try {
                    //主线程添加，但server端执行add时是在binder线程，所以可以想到这里的main线程应该会被挂起
                    bookManager.addListener(listener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.reg).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    bookManager.removeListener(listener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        findViewById(R.id.op).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = new Random().nextInt(100);
                Book b = new Book(i, "name" + i);
                try {
                    bookManager.addBook(b);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.op).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    int size = bookManager.getBookList().size();
                    String name = bookManager.getBookList().get(new Random().nextInt(size)).getBookName();
                    Toast.makeText(MainActivity.this, size + "  " + name, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
}
