package net.shopin.eventdispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import net.shopin.testviewevent.R;

public class Event_Activity extends AppCompatActivity {

    private static final String TAG = "Event_Activity";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_);

    }

    @Override
    public void onUserInteraction() {
        Log.d(TAG, "onUserInteraction: " + getApplicationContext());
    }

    public void btnClick(View view) {

    }
}
