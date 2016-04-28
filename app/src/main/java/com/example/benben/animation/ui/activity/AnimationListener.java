package com.example.benben.animation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.benben.animation.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by benben on 2016/4/25.
 * animationListener是一个监听器，该监听器在动画执行的各个阶段会得到通知，从而调用相应的方法
 * AnimationListener主要包括以下是三个方法
 * 1.onAnimationEnd（Animation animation）当动画结束市调用
 * 2.onAnimationRepeat（Animation animation）当动画重复时调用
 * 3.onAnimationStart（Animation animation)当动画启动时调用
 */
public class AnimationListener extends BaseActivity {
    @InjectView(R.id.listener_add)
    Button mAdd;
    @InjectView(R.id.listener_delete)
    Button mDelete;
    @InjectView(R.id.listener_imageView)
    ImageView mImageView;
    @InjectView(R.id.listener_layout)
    RelativeLayout mLayout;

    public static void startAnimationListener(Activity activity) {
        Intent intent = new Intent(activity, AnimationListener.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationlistener);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.listener_add, R.id.listener_delete,R.id.topLeft})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listener_add:
                /*淡入*/
                AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(1000);
                animation.setStartOffset(500);
                /*创建一个新的Imageview*/
                ImageView newImageView = new ImageView(AnimationListener.this);
                newImageView.setImageResource(R.mipmap.hehe);
                mLayout.addView(newImageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                newImageView.startAnimation(animation);
                break;
            case R.id.listener_delete:
                /*淡出*/
                AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.0f);
                animation1.setDuration(1000);
                animation1.setStartOffset(500);
                /*为animation对象设置监听器*/
                animation1.setAnimationListener(new RemoveAnimationListener());
                mImageView.startAnimation(animation1);
                /**
                 * 可以直接删除
                 *  mLayout.removeView(mImageView);
                 */
                break;
            case R.id.topLeft:
                finish();
                break;
        }
    }

    private class RemoveAnimationListener implements Animation.AnimationListener {

        /*动画执行完时remove*/

        @Override
        public void onAnimationEnd(Animation animation) {
            System.out.println("onAnimationEnd");
            mLayout.removeView(mImageView);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            System.out.println("onAnimationEnd");
        }

        @Override
        public void onAnimationStart(Animation animation) {
            System.out.println("onAnimationEnd");
        }
    }
}
