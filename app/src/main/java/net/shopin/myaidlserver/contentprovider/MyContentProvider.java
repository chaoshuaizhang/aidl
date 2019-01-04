package net.shopin.myaidlserver.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {
    private static final String TAG = "CLIENT_ERROR_LOG";

    private SQLiteDatabase openHelper;
    int book_id = 1001;
    UriMatcher uriMatcher;

    public MyContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int delete = openHelper.delete(getTableName(uri), selection, selectionArgs);
        return delete;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long book = openHelper.insert("book", null, values);
        Log.e(TAG, "insert: " + book, null);
        return uri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        openHelper = new MyOpenHelper(getContext()).getWritableDatabase();
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("content://net.shopin.contentprovider", "book", book_id);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        Log.e(TAG, "query: " + uri.getPath());
        Cursor book = openHelper.query("book", projection, selection, selectionArgs, null, null, sortOrder, null);
        return book;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public String getTableName(Uri uri) {
        int match_code = uriMatcher.match(uri);
        if (match_code == book_id) {
            return "book";
        } else {
            return null;
        }
    }

}
