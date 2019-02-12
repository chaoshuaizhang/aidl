package net.shopin.eventdispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.shopin.testviewevent.R;

public class Event_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_);
        View decorView = getWindow().getDecorView();

    }

    @Override
    public void onUserInteraction() {

    }
}
