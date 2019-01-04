package net.shopin.myaidlserver.contentprovider;

import android.content.Context;
import android.content.UriMatcher;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

/**
 * Created by zcs on 2018/9/12.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "CLIENT_ERROR_LOG";
    private static String dbName = "mydb.db";
    private String tableName = "book";

    public MyOpenHelper(Context context) {
        this(context, dbName, null, 2);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: 开始建库");
        db.execSQL("create table if not exists " + tableName + "(bookId Text primary key, bookName Text, bookDesc Text)");
        Log.e(TAG, "onCreate: 完成建库");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade: old=" + oldVersion + "     new=" + newVersion);
    }
}
