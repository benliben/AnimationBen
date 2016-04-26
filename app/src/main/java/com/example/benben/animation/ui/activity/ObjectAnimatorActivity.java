package com.example.benben.animation.ui.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.benben.animation.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by tangjunjie on 2016/4/25.
 */
public class ObjectAnimatorActivity extends BaseActivity {
    @InjectView(R.id.topLeft)
    ImageView mLeft;
    @InjectView(R.id.object_imageView)
    ImageView mImageView;
    @InjectView(R.id.object_content)
    RelativeLayout mContent;

    public static void startObjectAnimator(Activity activity) {
        Intent intent = new Intent(activity, ObjectAnimatorActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectanimator);
        ButterKnife.inject(this);
    }


    /*围绕x轴转一圈*/
    public void rotateyAnimRun(View view) {
        android.animation.ObjectAnimator
                .ofFloat(view, "rotationX", 0.0F, 360.0F)
                .setDuration(500)
                .start();
    }

    /*收缩并淡出*/
    public void rotateyAnimRun1(final View view) {
        ObjectAnimator anim=ObjectAnimator
                .ofFloat(view,"zhy",1.0f,0.0f)
                .setDuration(1000);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    /*实现一个动画更改多个效果*/
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();

    }
    @OnClick({R.id.topLeft, R.id.object_imageView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topLeft:
                finish();
                break;
            case R.id.object_imageView:
//                rotateyAnimRun(view);
//                rotateyAnimRun1(view);
                propertyValuesHolder(view);
                break;
        }
    }
}
