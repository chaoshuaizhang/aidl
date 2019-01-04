package net.shopin.myaidlclient;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ContentProviderActivity extends AppCompatActivity {

    private static final String TAG = "CLIENT_ERROR_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    public void btnCLick(View view) {
        Uri uri = Uri.parse("content://net.shopin.contentprovider/book");
        Cursor query = getContentResolver().query(uri, null, null, null, null);
        Toast.makeText(this, query.getCount() + "", Toast.LENGTH_SHORT).show();
        while (query.moveToNext()){
            Log.e(TAG, "queryCLick: "+query.getString(1) );
        }

    }
}
