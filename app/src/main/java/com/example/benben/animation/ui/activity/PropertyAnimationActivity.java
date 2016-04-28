package com.example.benben.animation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.benben.animation.R;
import com.example.benben.animation.model.MainModel;
import com.example.benben.animation.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by benben on 2016/4/25.
 * 属性动画 通过动画的方式改变对象的属性
 * 属性：
 * 1.Duration 动画的持续时间，默认是300ms
 * 2.Time interpolation 时间差值
 * 3.Repeat count and behavior；重复次数和模式
 * 4.Animator sets；动画集合，可以定义一组动画，一起执行或者顺序执行。
 * 5.Frame refresh delay；帧刷新延迟，对于设定的动画多久刷新一次默认为10ms
 * 6.ObjectAnimator；动画执行类
 * 7.ValueAnimator；动画执行类
 * 8.AnimatorSet；用于控制一组动画的执行：线性，一起，每个动画的先后执行等
 * 9.AnimatorInflater；用户加载属性的动画的xml文件
 * 10.TypeEvaluator；类型估值，主要用于设置动画属性的值
 * 11.TimeInterpolator；时间插值
 * <p/>
 * <p/>
 * ObjectAnimator 实现动画
 * valueAnimator 实现动画
 * AnimatorSet 使用动画
 */
public class PropertyAnimationActivity extends BaseActivity {
    @InjectView(R.id.propertyAnimation_content)
    RecyclerView mAnimationContent;

    public static void startPropertyAnimationActivity(Activity activity) {
        Intent intent = new Intent(activity, PropertyAnimationActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    private String[] mData = {"ObjectAnimator", "ValueAnimator"};
    private List<MainModel> mModel;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyanimation);
        ButterKnife.inject(this);
        initData();
        initView();
    }

    private void initData() {
        mModel = new ArrayList<>();
        for (int i = 0; i < mData.length; i++) {
            MainModel model = new MainModel();
            model.setName(mData[i]);
            mModel.add(model);
        }
    }

    private void initView() {
        mAnimationContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(mData);
        mAnimationContent.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {
                switch (position) {
                    case 0:
                        ObjectAnimatorActivity.startObjectAnimator(PropertyAnimationActivity.this);
                        break;
                    case 1:
                        ValueAnimatorActivity.startValueAnimator(PropertyAnimationActivity.this);
                        break;
                }
            }
        });
    }

    @OnClick(R.id.topLeft)
    public void onClick() {
        finish();
    }
}
