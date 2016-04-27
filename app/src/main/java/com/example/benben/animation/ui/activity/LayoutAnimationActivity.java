package com.example.benben.animation.ui.activity;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.benben.animation.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by tangjunjie on 2016/4/27.
 */
public class LayoutAnimationActivity extends BaseActivity {


    public static void startLayoutAnimation(Activity activity) {
        Intent intent = new Intent(activity, LayoutAnimationActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

        @InjectView(R.id.layoutAnimation_add)
    Button mAnimationAdd;
    @InjectView(R.id.layoutAnimation_appear)
    CheckBox mAnimationAppear;
    @InjectView(R.id.layoutAnimation_change_appearing)
    CheckBox mAnimationChangeAppearing;
    @InjectView(R.id.layoutAnimation_disappearing)
    CheckBox mAnimationDisappearing;
    @InjectView(R.id.layoutAnimation_change_disappearing)
    CheckBox mAnimationChangeDisappearing;
    @InjectView(R.id.layoutAnimation_content)
    ViewGroup mAnimationContent;
//    @InjectView(R.id.layoutAnimation_gridLayout)
//    GridLayout mGridlayout;

    private GridLayout mGridLayout;


    private LayoutTransition mTransition;
    private int mVal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutanimation);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        // 创建一个GridLayout
        mGridLayout = new GridLayout(this);
        // 设置每列5个按钮
        mGridLayout.setColumnCount(5);
        // 添加到布局中
        mAnimationContent.addView(mGridLayout);
        //默认动画全部开启
        mTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(mTransition);
    }

    /**
     * 添加按钮
     */
    public void addBtn(View view) {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mGridLayout.removeView(button);
            }
        });
    }

    @OnClick(R.id.layoutAnimation_add)
    public void onClick() {
//
//        final Button button = new Button(this);
//        button.setText(++mVal + "");
//        mGridlayout.addView(button, Math.min(1, mGridlayout.getChildCount()));
////        mGridlayout.removeView(mAnimationAdd);
    }


    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mTransition = new LayoutTransition();
        mTransition.setAnimator(
                LayoutTransition.APPEARING,
                (mAnimationAppear.isChecked() ? mTransition
                .getAnimator(LayoutTransition.APPEARING):null));
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
                (mAnimationChangeAppearing.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_APPEARING) : null));
        mTransition.setAnimator(LayoutTransition.DISAPPEARING,
                (mAnimationDisappearing.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                (mAnimationChangeDisappearing.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null));
        mGridLayout.setLayoutTransition(mTransition);
    }
}
