package net.shopin.testviewevent;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.TextView;

/**
 * Created by zcs on 2019/qqq/22.
 */

public class MyCustomTV extends AppCompatTextView {
    private static final String TAG = "MyCustomTV";

    public MyCustomTV(Context context) {
        super(context);
    }

    public MyCustomTV(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomTV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        //滑动速度
        Log.d(TAG, "onTouchEvent---velocityTracker.getXVelocity: " + velocityTracker.getXVelocity());
        Log.d(TAG, "onTouchEvent---velocityTracker.getYVelocity: " + velocityTracker.getYVelocity());
        velocityTracker.clear();
        velocityTracker.recycle();
        return super.onTouchEvent(event);
    }

}
