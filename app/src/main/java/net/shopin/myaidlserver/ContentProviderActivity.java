package net.shopin.myaidlserver;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class ContentProviderActivity extends AppCompatActivity {
    private static final String TAG = "CLIENT_ERROR_LOG";
    String authorities = "net.shopin.contentprovider";
    Uri bookUri = Uri.parse("content://" + authorities + "/book");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    public void queryCLick(View view) {
        //SELECT bookId, bookName, bookDesc FROM book WHERE bookName=? and bookDesc=?
        Cursor query = getContentResolver().query(bookUri, new String[]{"bookId", "bookName", "bookDesc"}, "bookName=? and bookDesc=?", new String[]{"书名", "描述"}, null);
        while (query.moveToNext()) {
            Log.e(TAG, "queryCLick: " + query.getExtras().toString());
        }
        Toast.makeText(this, "" + query.getCount(), Toast.LENGTH_SHORT).show();
    }

    public void insertCLick(View view) {
        ContentValues values = new ContentValues();
        values.put("bookId", "100" + new Random().nextInt(100));
        values.put("bookName", "谁许传" + values.get("bookId"));
        values.put("bookDesc", "四大名著之一" + values.get("bookName"));
        getContentResolver().insert(bookUri, values);
    }

}
