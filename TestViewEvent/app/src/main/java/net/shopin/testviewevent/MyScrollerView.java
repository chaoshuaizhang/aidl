package net.shopin.testviewevent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by zcs on 2019/1/30.
 */

public class MyScrollerView extends View {

    private static final String TAG = "MyScrollerView";
    Scroller scroller;

    public MyScrollerView(Context context) {
        this(context, null);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        canvas.drawText("哈哈", 0, 20, paint);
    }

    public void smoothScrollTo(int targetX, int targetY) {
        //起始点-X轴方向
        int scrollX = getScrollX();
        //需要滑动X轴的距离
        int willSmothX = targetX - scrollX;
        //起始点-Y轴方向
        int scrollY = getScrollY();
        //需要滑动Y轴的距离
        int willSmothY = targetY - scrollY;
        //注意此时并不是真的开始滑动，startScroll只是保存了我们传递的这些参数
        scroller.startScroll(scrollX, scrollY, willSmothX, willSmothY, 2000);
        //重绘
        invalidate();
    }

    @Override
    public void computeScroll() {
        Log.d(TAG, "computeScroll: X=" + scroller.getCurrX() + "  Y=" + scroller.getCurrY());
        //computeScrollOffset判断知否需要终止scrollTo的调用（即是否滑动到指定位置了？）
        //computeScrollOffset会根据设置的时间来分配每一时间段需要滑动的距离
        if (scroller.computeScrollOffset()) {
            //重绘到新的位置
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            //这里的postInvalidate重绘是指继续检测是否需要重绘，而不是去重绘
            postInvalidate();
        }
    }
//
//    //ViewGroup的事件体系
//    public boolean dispatchTouchEvent(MotionEvent event){
//        //是否消费
//        boolean consume = false;
//        //是否拦截
//        if(onInterceptTouchEvent(event)){
//            consume = onTouchEvent(event);
//        }else{
//            consume = child.dispatchTouchEvent(event);
//        }
//        return consume;
//    }
//
//    //View的事件体系
//    public boolean dispatchTouchEvent(MotionEvent event){
//        //是否消费
//        boolean consume = false;
//        //是否拦截
//        if(onTouch(event)){
//            consume = onTouchEvent(event);
//        }else{
//            consume = child.dispatchTouchEvent(event);
//        }
//        return consume;
//    }
}
