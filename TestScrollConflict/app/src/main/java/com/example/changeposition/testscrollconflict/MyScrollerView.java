package com.example.changeposition.testscrollconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 外部拦截法
 * 解决思路：当滑动ListView时，直到
 * ListView滑动到头部或尾部，父View才可以滑动
 */
public class MyScrollerView extends ScrollView {
    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (parentCanIntercept()) {

                }
                intercept = false;
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    private boolean parentCanIntercept() {
        return false;
    }
}
