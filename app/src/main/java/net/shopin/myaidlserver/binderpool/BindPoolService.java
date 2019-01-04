package net.shopin.myaidlserver.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import net.shopin.myaidlserver.Book;
import net.shopin.myaidlserver.BookListener;
import net.shopin.myaidlserver.IBinderPool;
import net.shopin.myaidlserver.IBookManager;

import java.util.List;

public class BindPoolService extends Service {

    BookManagerImpl bookManager = new BookManagerImpl();

    //简易连接池
    IBinder mBinder = new IBinderPool.Stub() {

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public IBinder getBinderByTag(int tag) throws RemoteException {
            //根据tag找指定的binder
            IBinder binder = null;
            switch (tag) {
                case 1001:
                    binder = bookManager;
                    break;
                case 1002:
                    //上边下边两种方式一行
                    binder = new IBookManager.Stub() {
                        @Override
                        public List<Book> getBookList() throws RemoteException {
                            return null;
                        }

                        @Override
                        public void addBook(Book book) throws RemoteException {

                        }

                        @Override
                        public void addListener(BookListener listener) throws RemoteException {

                        }

                        @Override
                        public void removeListener(BookListener listener) throws RemoteException {

                        }
                    };
                    break;
            }
            return binder;
        }

        @Override
        public IBookManager getBinderByTagFun2(int tag) throws RemoteException {
            return IBookManager.Stub.asInterface(bookManager.asBinder());
        }
    };

    public BindPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
