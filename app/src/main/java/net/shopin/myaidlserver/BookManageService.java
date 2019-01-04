package net.shopin.myaidlserver;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManageService extends Service {
    private static final String TAG = "CLIENT_ERROR_LOG";
    private CopyOnWriteArrayList<Book> list = new CopyOnWriteArrayList<>();
    private List<BookListener> listeners = new ArrayList<>();
    private RemoteCallbackList<BookListener> cbList = new RemoteCallbackList<>();

    /**
     * 创建这个Binder就是为了暴露给客户端，客户端拿到binder执行add，get方法时，实际走的就是这里。
     * IBookManager.aidl的这4个方法都执行在binder线程中
     */
    private IBinder mBinder = new IBookManager.Stub() {

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Log.e(TAG, "server   onTransact: ------------------" + code);
            int check1 = checkCallingOrSelfPermission("net.shopin.myaidlserver.book_service_perr");
            int check2 = checkCallingOrSelfPermission("net.shopin.myaidlserver.perrrrr");
            if (check1 == PackageManager.PERMISSION_DENIED && check2 == PackageManager.PERMISSION_DENIED) {
                Log.e(TAG, "onBind: 第二种方式：没有权限  " + check1 + "   " + check2);
                return false;
            } else {
                Log.e(TAG, "onBind: 第二种方式：有权限");
            }

            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return list;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.e(TAG, "addBook: " + Thread.currentThread().getName());
            list.add(book);
//            listeners.get(0).hasBook();
            for (int i = 0, size = cbList.beginBroadcast(); i < size; i++) {
                cbList.getBroadcastItem(i).hasBook();
            }
            cbList.finishBroadcast();
        }

        @Override
        public void addListener(BookListener listener) throws RemoteException {
//            if (listeners.contains(listener)) {
//                Log.e(TAG, "addListener: 已存在");
//            } else {
//                Log.e(TAG, "addListener: 不存在---添加");
//                listeners.add(listener);
//            }
            //添加是在client的主线程中add的，但是这里走到了binder线程
            Log.e(TAG, "addListener: " + Thread.currentThread().getName());
            cbList.register(listener);
        }

        @Override
        public void removeListener(BookListener listener) throws RemoteException {
//            if (listeners.contains(listener)) {
//                Log.e(TAG, "removeListener: 存在---移除");
//                listeners.remove(listener);
//            } else {
//                Log.e(TAG, "removeListener: 不存在---不移除");
//            }
            //执行在binder线程
            cbList.unregister(listener);
            Log.e(TAG, "removeListener: " + Thread.currentThread().getName());
            listener.noBook();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        list.add(new Book(1001, "谁许传"));
        list.add(new Book(1002, "我许传"));
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
        return mBinder;
    }

}
