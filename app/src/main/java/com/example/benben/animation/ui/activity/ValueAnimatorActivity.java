package com.example.benben.animation.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benben.animation.R;


import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by benben on 2016/4/25.
 */
public class ValueAnimatorActivity extends BaseActivity {
    @InjectView(R.id.topLeft)
    ImageView mLeft;
    @InjectView(R.id.value_imageView)
    ImageView mImageView;
    @InjectView(R.id.value_text1)
    TextView mText1;
    @InjectView(R.id.value_text2)
    TextView mText2;
    @InjectView(R.id.value_text3)
    TextView mText3;
    @InjectView(R.id.value_text4)
    TextView mText4;
    @InjectView(R.id.value_text5)
    TextView mText5;


    public static void startValueAnimator(Activity activity) {
        Intent intent = new Intent(activity, ValueAnimatorActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void startPropertyAnimationActivity(Activity activity) {
        Intent intent = new Intent(activity, PropertyAnimationActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valueanimator);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.topLeft, R.id.value_text1, R.id.value_text2, R.id.value_text3, R.id.value_text4, R.id.value_text5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topLeft:
                finish();
                break;
            case R.id.value_text1:
                verticalRun(view);
                break;
            case R.id.value_text2:
                paowuxian(view);
                break;
            case R.id.value_text3:
                fadeOut(view);
                break;
            case R.id.value_text4:
                togetherRun(view);
                break;
            case R.id.value_text5:
                playWithAfter(view);
                break;
        }
    }


    /**
     * 自由落体
     */
    public void verticalRun(View view) {


    int  mScreenHeight=ScreenUtilsBen.getScreenHeight(ValueAnimatorActivity.this);
        ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight- mImageView.getHeight());
        animator.setTarget(mImageView);
        animator.setDuration(1000).start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mImageView.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }


    /**
     * 抛物线
     */
    public void paowuxian(View view) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF();
                point.x = 200 * fraction * 3;//s=vt
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);//h=1/2*a*t*t
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mImageView.setX(point.x);
                mImageView.setY(point.y);
            }
        });
    }

    /**
     * 淡出且删除
     */

    public void fadeOut(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mImageView, "alpha", 0.5f);

        /**完整写法（复杂写法）*/
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("lyx", "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("lyx", "onAnimationEnd: ");
                ViewGroup parent = (ViewGroup) mImageView.getParent();
                if (parent != null) {
                    parent.removeView(mImageView);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("lyx", "onAnimationCancel: ");

            }
            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("lyx", "onAnimationRepeat: ");

            }
        });
        anim.start();

        Log.e("lyx", "onAnimationEnd: "+1);


        /**简单写法*/
//        anim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.e("lyx", "onAnimationEnd: ");
//                ViewGroup parent = (ViewGroup) mImageView.getParent();
//                if (parent != null) {
//                    parent.removeView(mImageView);
//                }
//            }
//        });
    }

    public void togetherRun(View view) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f, 2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        /**两个动画同时执行*/
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }

    public void playWithAfter(View view) {
        float cx = mImageView.getX();
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mImageView, "x", cx, 0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mImageView, "x", cx);

        /**
         * anim1,anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(1000);
        animSet.start();
    }

}
