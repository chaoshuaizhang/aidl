package net.shopin.testviewevent;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * 弹性滑动
 */
public class FlexSlideActivity extends AppCompatActivity {

    private static final String TAG = "FlexSlideActivity";
    MyScrollerView scrollerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_slide);
        scrollerView = (MyScrollerView) findViewById(R.id._scroll_view_);
        scrollerView.setClickable(true);
        scrollerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlexSlideActivity.this, "Click", Toast.LENGTH_SHORT).show();
                //使用Scroller来实现弹性滑动
                scrollerView.smoothScrollTo(15, 10);
                //使用动画的一些机制来实现弹性滑动
//                ValueAnimator animator = ValueAnimator.ofInt(0, 1000).setDuration(3000);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Log.d(TAG, "onAnimationUpdate: " + animation.getAnimatedValue() + "   " + animation.getAnimatedFraction());
//                        scrollerView.scrollTo(Integer.valueOf(String.valueOf(animation.getAnimatedValue())) / 10, Integer.valueOf(String.valueOf(animation.getAnimatedValue())) / 10);
//                    }
//                });
//                animator.start();
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, FlexSlideActivity.class);
        context.startActivity(starter);
    }

}
