package net.shopin.myaidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.shopin.myaidlserver.Book;
import net.shopin.myaidlserver.IBinderPool;
import net.shopin.myaidlserver.IBookManager;

import java.util.Random;

public class BinderPoolActivity extends AppCompatActivity {

    private static final String TAG = "CLIENT_ERROR_LOG";
    private IBinderPool binderPool;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderPool = IBinderPool.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: " + name.getClassName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        Intent intent = new Intent();
        intent.setAction("net.shopin.binder_pool");
        intent.setPackage("net.shopin.myaidlserver");
        bindService(intent, connection, BIND_AUTO_CREATE);
        findViewById(R.id.btn_pool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    IBinder binder = binderPool.getBinderByTag(1001);
//                    IBookManager bookManager = IBookManager.Stub.asInterface(binder);
//                    int i = new Random().nextInt(1000);
//                    bookManager.addBook(new Book(i, "name - " + i));

                    //----上边下边为两种写法，一个是返回Binder，需要我们重新构建IBookManager，一个直接返回IBookManager----------------------
                    //也就是说 真正用来操作CRUD的是IBookManager，Binder这是一个桥接的作用---核心在asInterface方法
                    IBookManager iBookManager = binderPool.getBinderByTagFun2(0);
                    int i = new Random().nextInt(1000);
                    iBookManager.addBook(new Book(i, "name - " + i));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_pool).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    //这块要注意，从binder中拿到的是tag对应的binder对象，最开始直接转为IBookManager了
                    //给我的感觉就是：getBinderByTag方法拿到的就是对应1001的binder，所以就直接转了
                    //事实上这个getBinderByTag内部会走transact--->onTransact--->最终返回一个binder
                    //注意：IBookManager不是binder类型，但是它asBinder返回binder就是下边的这个binder
                    //-------------------------------------------------------------------------
                    //其实这个原理和在ServiceConnection的onServiceConnected方法中是一样的，connect是直接把实际的binder传到
                    //onServiceConnected方法了，而连接池是传到连接池了，但是具体使用时都是一样的
                    IBinder binder = binderPool.getBinderByTag(1001);
                    IBookManager bookManager = IBookManager.Stub.asInterface(binder);
                    //      true
                    Log.e(TAG, "onLongClick: " + (bookManager.asBinder() == binder));
                    int size = bookManager.getBookList().size();
                    Log.e(TAG, "onLongClick: -----" + size);
                    Toast.makeText(BinderPoolActivity.this, "size=" + size, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
}
