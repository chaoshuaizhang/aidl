package net.shopin.testviewevent;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by zcs on 2019/1/24.
 * 关于返回值还有些疑问
 */

public class MyGestureListenerImpl implements GestureDetector.OnGestureListener {
    private static final String TAG = "MyGestureListenerImpl";

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown: ");
        //返回true，继续向下
        //返回false，直接返回到上边
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: ");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: ");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: ");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress: ");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: ");
        return false;
    }
}
