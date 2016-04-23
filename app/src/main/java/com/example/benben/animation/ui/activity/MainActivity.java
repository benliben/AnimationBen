package com.example.benben.animation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benben.animation.R;
import com.example.benben.animation.model.MainModel;
import com.example.benben.animation.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.topLeft)
    ImageView mLeft;
    @InjectView(R.id.topTitle)
    TextView mTitle;
    @InjectView(R.id.topRight)
    ImageView mRight;
    @InjectView(R.id.mainRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.mainImageView)
    ImageView mImageView;
    @InjectView(R.id.main_fresco_btn)
    Button startFrescoBtn;

    private List<MainModel> mModels;
    private MainAdapter mAdapter;
    private String[] mData = {"旋转", "缩放", "淡入淡出", "移动"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initData() {
        mModels = new ArrayList<>();
        for (int i = 0; i < mData.length; i++) {
            MainModel model = new MainModel();
            model.setName(mData[1]);
            mModels.add(model);
        }
    }

    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        mImageView = (ImageView) findViewById(R.id.mainImageView);
//        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*设置每个item的高度是否固定*/
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MainAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        Button startFrescoBtn = (Button) this.findViewById(R.id.main_fresco_btn);
        startFrescoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrescoActivity.startFrescoActivity(MainActivity.this);
            }
        });

        mAdapter.setOnClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {

                Log.i("JAM", position + "");
                /**
                 * 创建一个AnimationSet对象，参数为Boolean型
                 * true表示使用AnimationSet的interpolator，false则是使用自己的
                 */
                AnimationSet mset;
                mset = new AnimationSet(true);
                switch (position) {

                    case 0:
                        /**创建一个AlphaAnimation对象，参数从完全透明懂啊完全不透明*/
                        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                        /**设置动画执行时间*/
                        alphaAnimation.setDuration(500);
                        /**将alphaAnimation对象添加到AnimationSet当中*/
                        mset.addAnimation(alphaAnimation);
                        /**使用ImageView的setrtAnimation方法执行动画*/
                        mImageView.startAnimation(mset);
                        break;
                    case 1:
                        /**
                         * 参数1：从那个角度旋转开始
                         * 参数2：转到什么角度
                         * 参数3：确定x轴坐标的类型，有absolue绝对坐标，relative_to-self相对自身坐标
                         * relative_to_parent相对于父控件的坐标
                         * 参数4：x轴的值。0.5f表明是以自身这个控件的一半长度为x轴
                         * 参数5：确定y轴坐标的类型
                         * 参数6：y轴的值。0.5f表明是以自身这个控件的一半长度为y轴
                         *
                         * 后面4个参数用于设置围绕着旋转的圆的圆形
                         */
                        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                                Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0.5f);
                        rotateAnimation.setDuration(1000);
                        mset.addAnimation(rotateAnimation);
                        mImageView.startAnimation(mset);

                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, DesaltActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ShiftActivity.class));
                        break;

                }
            }
        });
    }

    @OnClick({R.id.topLeft, R.id.topRight, R.id.main_fresco_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topLeft:
                break;
            case R.id.topRight:
                break;
            case R.id.main_fresco_btn:
                Log.i("JAM", "---");


                break;
        }
    }
}
