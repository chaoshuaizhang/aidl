package net.shopin.myaidlserver;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManageService extends Service {
    private static final String TAG = "SERVER_ERROR_LOG";
    private CopyOnWriteArrayList<Book> list = new CopyOnWriteArrayList<>();
    /**
     * 使用：创建这个Binder就是为了暴露给客户端，客户端拿到binder，经过转换变为BookManager实例。
     * 执行add，get方法时，实际走的就是这里
     * IBookManager.aidl的这几个方法都执行在binder线程中。
     * 原理：可以看下IBookManager.Stub()的代码
     */
    private IBinder mBinder = new IBookManager.Stub() {

        @Override
        public IInterface queryLocalInterface(String descriptor) {
            //main线程
            Log.e(TAG, "queryLocalInterface: " + Thread.currentThread().getName());
            IInterface iInterface = super.queryLocalInterface(descriptor);
            if (iInterface != null) {
                if (iInterface instanceof net.shopin.myaidlserver.IBookManager) {
                    Log.e(TAG, "instanceof");
                } else {
                    Log.e(TAG, "!instanceof");
                }
            } else {
                Log.e(TAG, "iInterface == null");
            }
            return iInterface;
        }

        /**
         * 注意这是在匿名内部类中重写的onTransact方法
         * */
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            //binder线程，说明远程调用时执行相应操作的是在binder线程中了
            Log.e(TAG, "onTransact: " + Thread.currentThread().getName() + "   code= " + code);
            IBookManager.Stub.asInterface(null);
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.e(TAG, "getBookList: " + Thread.currentThread().getName());
            return list;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            IInterface iInterface = mBinder.queryLocalInterface("net.shopin.myaidlserver.IBookManager");
            //事实证明：在server端的service里执行queryLocalInterface是可以获取到对应的IInterface的
            //当前所在的是Binder进程
            if (iInterface != null) {
                if (iInterface instanceof net.shopin.myaidlserver.IBookManager) {
                    Log.e(TAG, "addBook instanceof");
                } else {
                    Log.e(TAG, "addBook !instanceof");
                }
            } else {
                Log.e(TAG, "addBook iInterface == null");
            }
            //当前是Binder线程
            Log.e(TAG, "addBook: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(book);
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        list.add(new Book(1001, "谁许传"));
        list.add(new Book(1002, "我许传"));
        //打印出 主线程
        Log.e(TAG, "onCreate: pid=" + Process.myPid() + "      th=" + Thread.currentThread().getName());
    }

    /**
     * 在客户端与服务端建立连接时传给客户端
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        int check = checkCallingOrSelfPermission("net.shopin.myaidlserver.book_service_perr");
//        if (check == PackageManager.PERMISSION_DENIED) {
//            Log.e(TAG, "onBind: 没有权限");
//            return null;
//        }
        //main线程
        Log.e(TAG, "onBind: " + Thread.currentThread().getName());
        return mBinder;
    }

}
