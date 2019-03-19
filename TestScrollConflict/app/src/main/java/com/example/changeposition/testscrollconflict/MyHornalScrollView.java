package com.example.changeposition.testscrollconflict;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;


/**
 * 自定义HorizontalScrollView
 */
public class MyHornalScrollView extends LinearLayout {

    private Scroller mScroller;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyHornalScrollView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyHornalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyHornalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void smothScrollTo(int targetX, int targetY) {
        int scrollX = getScrollX();
        int willScrollX = targetX - scrollX;
        int scrollY = getScrollY();
        int willScrollY = targetY - scrollY;
        mScroller.startScroll(scrollX, scrollY, willScrollX, willScrollY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}