package com.example.benben.animation.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.benben.animation.R;
import com.example.benben.animation.ui.activity.tools.ScreenUtilsBen;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by benben on 2016/4/27.
 */
public class ViewAnimActivity extends BaseActivity {

    public static void startViewAnimActivity(Activity activity) {
        Intent intent = new Intent(activity, ViewAnimActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
    @InjectView(R.id.id_ball)
    ImageView mBall;
    @InjectView(R.id.view_button_left)
    Button mButtonLeft;
    @InjectView(R.id.view_button_right)
    Button mButtonRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anim);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.view_button_left, R.id.view_button_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_button_left:
                viewAnim(view);
                break;
            case R.id.view_button_right:
                viewAnim(view);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void viewAnim(View view) {
        int mScreenHeight = ScreenUtilsBen.getScreenHeight(ViewAnimActivity.this);
        mBall.animate().alpha(0).y(mScreenHeight / 2).setDuration(1000)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("lyx", "START: ");
                    }
                }).withEndAction(new Runnable() {
            @Override
            public void run() {
                Log.i("lyx", "END: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBall.setY(0);
                        mBall.setAlpha(1.0f);
                    }
                });
            }
        }).start();
    }
}
