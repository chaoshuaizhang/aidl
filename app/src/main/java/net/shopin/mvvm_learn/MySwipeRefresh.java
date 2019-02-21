package net.shopin.mvvm_learn;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by zcs on 2019/2/20.
 */
//@InverseBindingMethods({
//        @InverseBindingMethod(type = MySwipeRefresh.class, attribute = "myrefresh"),
//})
public class MySwipeRefresh extends SwipeRefreshLayout {

    public boolean myrefresh;

    public MySwipeRefresh(Context context) {
        super(context);
    }

    public MySwipeRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean getMyrefresh(boolean b) {
        return myrefresh;
    }

    @BindingAdapter({"app:myrefresh"})
    public static void loadRefresh(SwipeRefreshLayout layout, boolean myrefresh) {
        Log.d("MainMaivityActivity", "loadRefresh: 进来了哈哈哈");
        layout.setRefreshing(myrefresh);
    }



    //    @BindingAdapter({"app:isRefreshing"})
//    public static void loadRefresh(MySwipeRefresh layout, boolean swipeRefresh) {
//        Log.d("MainMaivityActivity", "loadRefresh: ");
//        layout.setRefreshing(swipeRefresh);
//    }


}
