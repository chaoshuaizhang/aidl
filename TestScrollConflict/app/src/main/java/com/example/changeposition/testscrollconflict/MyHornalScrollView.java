package com.example.changeposition.testscrollconflict;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;


/**
 * 自定义HorizontalScrollView
 */
public class MyHornalScrollView extends ViewGroup {

    private String TAG = "MyHornalScrollView";
    private Scroller mScroller;
    private LinearLayout container;
    private ListView listView;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private int lastX;
    private int lastY;
    private int startX;
    private int startY;
    private LinearLayout rightLayout;
    private VelocityTracker mVelocityTracker;
    private int mMaxVelocity;
    private int pointerId;
    private boolean up = false;

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
        mVelocityTracker = VelocityTracker.obtain();
        mMaxVelocity = ViewConfiguration.get(context).getMaximumFlingVelocity();
    }

    public void setListview(ListView listView) {
        this.listView = listView;
        left = listView.getLeft() + listView.getPaddingLeft();
        top = listView.getTop();
        right = listView.getRight();
        bottom = listView.getBottom();
        rightLayout = findViewById(R.id.container).findViewById(1008611);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pointerId = event.getPointerId(0);
                startX = (int) event.getX();
                startY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (event.getY() > startY) {
                    up = true;
                    Rect rect = new Rect();
                    rightLayout.getChildAt(0).getLocalVisibleRect(rect);
                    if (rect.top >= 0 || rect.bottom >= rightLayout.getChildAt(0).getMeasuredHeight()) {
                        break;
                    }
                } else {
                    up = false;
                    Rect rect1 = new Rect();
                    rightLayout.getChildAt(rightLayout.getChildCount() - 1).getLocalVisibleRect(rect1);
                    if (rect1.bottom == rightLayout.getChildAt(0).getMeasuredHeight()) {
                        break;
                    }
                }
                //判断是否滑动到头部、底部
                int x = (int) (event.getX() - lastX);
                int y = (int) (event.getY() - lastY);
                Log.d(TAG, "onTouchEvent: " + y);
                //方式1
//                scrollBy(0, -y);
                //方式2
                smothScrollTo(0, -y);
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
//                mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
//                float velocityX = mVelocityTracker.getXVelocity(pointerId);
//                float velocityY = mVelocityTracker.getYVelocity(pointerId);
//                Log.d("uuuuuppppp", "onTouchEvent: " + event.getY());
//                mScroller.fling(0, (int) event.getY(), 0, (int) velocityY, 0, 0, (int) -(event.getY() + 100), (int) -(event.getY() + 200));
//                invalidate();
                if (up) {
                    mScroller.startScroll(0, rightLayout.getScrollY(), 0, -200, 1500);
                } else {
                    mScroller.startScroll(0, rightLayout.getScrollY(), 0, 200, 1500);
                }
                break;
        }
        return true;
    }

    public void smothScrollTo(int targetX, int targetY) {
        int scrollY = rightLayout.getScrollY();
        Log.d("cvhgdvschdvshcd", "smothScrollTo: " + scrollY + "    " + targetY);
        mScroller.startScroll(0, scrollY, 0, targetY, 0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            rightLayout.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: 按下");
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (parentCanIntercept(event)) {
                    Log.d(TAG, "onInterceptTouchEvent: 移动");
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        return intercept;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;
        if (getChildCount() == 0) {
        } else {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                width += child.getMeasuredWidth();
                height += child.getMeasuredHeight();
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(left, 0, left + child.getMeasuredWidth(), child.getMeasuredHeight());
            left += child.getWidth();
        }
    }

    private int getSizeByMeasureSpec(int measureSpec) {
        int size = 0;
        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.AT_MOST:
                size = MeasureSpec.getSize(measureSpec);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.EXACTLY:
                size = MeasureSpec.getSize(measureSpec);
                break;
        }
        return size;
    }

    private boolean parentCanIntercept(MotionEvent event) {
        if (event.getRawX() > left + listView.getWidth() || event.getRawX() < left) {
            //点击在listview外边
            return true;
        } else {
            //点击在listview里边
            return false;
        }
    }
}