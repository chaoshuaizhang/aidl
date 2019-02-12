package net.shopin.testviewevent;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 三种滑动的实现
 */
public class ViewSlideActivity extends AppCompatActivity {

    private static final String TAG = "ViewSlideActivity";
    TextView tvSlide;
    TextView tvAnimationSlide;
    TextView tvLayoutParamsSlide;
    float oldRawX;
    float rawX;
    float oldRawY;
    float rawY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slide);
        tvSlide = (TextView) findViewById(R.id.tv_slide);
        tvAnimationSlide = (TextView) findViewById(R.id.tv_animation_slide);
        tvLayoutParamsSlide = (TextView) findViewById(R.id.tv_layout_params_slide);
        init();
    }

    void init() {
        //通过scrollerBy滑动
        tvSlide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "onTouch: DOWN");
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    Log.d(TAG, "onTouch: MOVE");
                    rawX = event.getRawX();
                    rawY = event.getRawY();
                    //注意滑动的是TV中的文字
                    tvSlide.scrollBy((int) (oldRawX - rawX), (int) (oldRawY - rawY));
                }
                oldRawX = event.getRawX();
                oldRawY = event.getRawY();
                return false;
            }
        });
        //使用动画滑动
        tvAnimationSlide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    rawX = event.getRawX();
                    rawY = event.getRawY();
                    //tvAnimationSlide.setTranslationX(tvAnimationSlide.getTranslationX() + rawX - oldRawX);
                    //tvAnimationSlide.setTranslationY(tvAnimationSlide.getTranslationY() + rawY - oldRawY);
                    //上边和下边意义一样
                    ObjectAnimator.ofFloat(tvAnimationSlide, "translationX", tvAnimationSlide.getTranslationX() + rawX - oldRawX).setDuration(0).start();
                    ObjectAnimator.ofFloat(tvAnimationSlide, "translationY", tvAnimationSlide.getTranslationY() + rawY - oldRawY).setDuration(0).start();
                    //下边的动画平移的不是位置，所以点击事件什么的还是在原来的位置
                    TranslateAnimation translateAnimation = new TranslateAnimation(1f, 1f, 1f, 1f);
                    tvLayoutParamsSlide.startAnimation(translateAnimation);
                }
                oldRawX = event.getRawX();
                oldRawY = event.getRawY();
                return false;
            }
        });
        //动态设置layoutParams
        tvLayoutParamsSlide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    rawX = event.getRawX();
                    rawY = event.getRawY();
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tvLayoutParamsSlide.getLayoutParams();
                    layoutParams.setMargins(layoutParams.leftMargin - (int) (oldRawX - rawX),
                            layoutParams.topMargin - (int) (oldRawY - rawY),
                            layoutParams.rightMargin + (int) (oldRawX - rawX),
                            layoutParams.bottomMargin - (int) (oldRawY - rawY));
                    tvLayoutParamsSlide.setLayoutParams(layoutParams);
                }
                oldRawX = event.getRawX();
                oldRawY = event.getRawY();
                return false;
            }
        });
        //注意:onTouch事件返回true时，就轮不到click执行了
        tvSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewSlideActivity.this, "Slide", Toast.LENGTH_SHORT).show();
            }
        });
        tvAnimationSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewSlideActivity.this, "Animation", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewSlideActivity.class);
        context.startActivity(starter);
    }

}
