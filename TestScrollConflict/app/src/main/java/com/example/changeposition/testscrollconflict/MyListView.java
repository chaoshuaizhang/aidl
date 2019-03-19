package com.example.changeposition.testscrollconflict;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * 内部拦截法
 * 解决思路：当滑动ListView时，直到
 * ListView滑动到头部或尾部，父View才可以滑动
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {

    private View topView;
    private View bottomView;
    private String TAG = "MyListMyListViewView";
    private int mFirstVisibleItem;
    private int mVisibleItemCount;
    private int mTotalItemCount;
    private ScrollView scrollContainer;

    public int getmFirstVisibleItem() {
        return mFirstVisibleItem;
    }

    public void setmFirstVisibleItem(int mFirstVisibleItem) {
        this.mFirstVisibleItem = mFirstVisibleItem;
    }

    public int getmVisibleItemCount() {
        return mVisibleItemCount;
    }

    public void setmVisibleItemCount(int mVisibleItemCount) {
        this.mVisibleItemCount = mVisibleItemCount;
    }

    public int getmTotalItemCount() {
        return mTotalItemCount;
    }

    public void setmTotalItemCount(int mTotalItemCount) {
        this.mTotalItemCount = mTotalItemCount;
    }

    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setMyParent(LinearLayout viewGroup) {
        scrollContainer = (ScrollView) viewGroup.getParent();
        setOnScrollListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //不允许父View拦截down事件
                scrollContainer.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (parentCanScroll()) {
                    //父View可以滑动
                    scrollContainer.requestDisallowInterceptTouchEvent(false);
                } else {
                    //父View不可以滑动
                    scrollContainer.requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    /**
     * 在当前LoistView滑动到顶部、尾部时父View可以滑动
     *
     * @return
     */
    private boolean parentCanScroll() {
        if (getFirstVisiblePosition() == 0) {
            View firstVisibleItemView = getChildAt(0);
            if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                return true;
            }
        } else if ((getFirstVisiblePosition() + getChildCount()) == getCount()) {
            View lastVisibleItemView = getChildAt(getChildCount() - 1);
            if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == getHeight()) {
                //getBottom是view底部距离父View顶部的距离
                return true;
            }
        }
        return false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                Log.d("onScrollStateChanged", "onScrollStateChanged: 滚动事件结束的时候调用，调用一次");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.d("onScrollStateChanged", "onScrollStateChanged: 开始滚动的时候调用，调用一次");
                break;
            case SCROLL_STATE_FLING:
                Log.d("onScrollStateChanged", "onScrollStateChanged: 当手指离开屏幕，并且产生惯性滑动的时候调用，可能会调用<=1次");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        setFirstVisibleItem(firstVisibleItem);
//        setVisibleItemCount(visibleItemCount);
//        setTotalItemCount(totalItemCount);
        Log.d("MyListMyListViewView", "onScroll: " + firstVisibleItem + "  " + visibleItemCount + "   " + totalItemCount);
    }

}
