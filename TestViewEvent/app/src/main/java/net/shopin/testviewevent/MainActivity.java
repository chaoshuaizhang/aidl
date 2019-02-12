package net.shopin.testviewevent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    View tvOnTouch = null;
    View tvGesture = null;
    ViewGroup viewGroup = null;
    private float poiX;
    private float poiY;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOnTouch = findViewById(R.id.tv_ontouch);
        tvGesture = findViewById(R.id.tv_gesture);
        viewGroup = (ViewGroup) findViewById(R.id.tv_container);
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "VG-Click", Toast.LENGTH_SHORT).show();
            }
        });
        tvOnTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.tv_ontouch) {
                    Log.d(TAG, "onTouch---getAction: " + event.getAction());
                    Log.d(TAG, "onTouch---getX: " + event.getX());
                    Log.d(TAG, "onTouch---getY: " + event.getY());
                    Log.d(TAG, "onTouch---getRawX: " + event.getRawX());
                    Log.d(TAG, "onTouch---getRawY: " + event.getRawY());
                    checkIsScroll(v, event);
                }
                return false;
            }
        });
        gestureDetector = new GestureDetector(this, new MyGestureListenerImpl());
        tvGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //使用Gesture来处理事件
                return gestureDetector.onTouchEvent(event);
            }
        });

    }

    public void viewClick(View view) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.d(TAG, "viewClick---density-: " + dm.density);
        Log.d(TAG, "viewClick---打印View-: " + view.getId());
        Log.d(TAG, "viewClick---getLeft: " + view.getLeft());
        Log.d(TAG, "viewClick---getTop: " + view.getTop());
        Log.d(TAG, "viewClick---getX: " + view.getX());
        Log.d(TAG, "viewClick---getY: " + view.getY());
        Log.d(TAG, "viewClick---getTranslationX: " + view.getTranslationX());
        Log.d(TAG, "viewClick---getTranslationY: " + view.getTranslationY());
        if (view.getId() == R.id.tv_ontouch) {
            //tvHello.scrollBy(2, 5);//只移动文字，不移动textView
            tvOnTouch.setTranslationY(2f);
        }
    }

    private void checkIsScroll(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int touchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
            Log.d(TAG, "checkIsScroll---touchSlop: " + touchSlop);
            int offsetX = (int) (event.getRawX() - poiX);
            int offsetY = (int) (event.getRawY() - poiY);
            //滑动偏移量大于touchSlop时才能滑动
            if (Math.abs(offsetX) > touchSlop)
                v.offsetLeftAndRight(offsetX);
            v.offsetTopAndBottom(offsetY);
        }
        poiX = event.getRawX();
        poiY = event.getRawY();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }
}
