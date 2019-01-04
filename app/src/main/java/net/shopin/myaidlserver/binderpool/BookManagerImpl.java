package net.shopin.myaidlserver.binderpool;

import android.os.RemoteException;
import android.util.Log;

import net.shopin.myaidlserver.Book;
import net.shopin.myaidlserver.BookListener;
import net.shopin.myaidlserver.IBookManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zcs on 2018/9/12.
 */

public class BookManagerImpl extends IBookManager.Stub {
    private static final String TAG = "CLIENT_ERROR_LOG";
    List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getBookList() throws RemoteException {
        Log.e(TAG, "getBookList: ");
        return books;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Log.e(TAG, "addBook: ");
        books.add(book);
    }

    @Override
    public void addListener(BookListener listener) throws RemoteException {

    }

    @Override
    public void removeListener(BookListener listener) throws RemoteException {

    }
}
