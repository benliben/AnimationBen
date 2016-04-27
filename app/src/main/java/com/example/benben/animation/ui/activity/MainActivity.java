package com.example.benben.animation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.benben.animation.R;
import com.example.benben.animation.model.MainModel;
import com.example.benben.animation.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tangjunjie on 2016/4/25.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.main_recyclerView)
    RecyclerView mRecyclerView;

    private List<MainModel> mModels;
    private MainAdapter mAdapter;
    private String[] mData = {"Animation", "LayoutAnimationController","AnimationListener",
                                "PropertyAnimation","LayoutAnimation(不成功","VIewAnim(不成功"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian);
        ButterKnife.inject(this);
        initData();
        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {
                Log.i("lyx", "+++++++position+++++: "+position);
                switch (position) {
                    case 0:
                        AnimationActivity.startAnimationActivity(MainActivity.this);
                        break;
                    case 1:
                        LayoutAnimationControllerActivity.startLayoutAnimationControllerActivity(MainActivity.this);
                        break;
                    case 2:
                        AnimationListener.startAnimationListener(MainActivity.this);
                        break;
                    case 3:
                        PropertyAnimationActivity.startPropertyAnimationActivity(MainActivity.this);
                        break;
                    case 4:
                        LayoutAnimationActivity.startLayoutAnimation(MainActivity.this);
                        break;
                    case 5:
                        ViewAnimActivity.startViewAnimActivity(MainActivity.this);

                }
            }
        });
    }

    private void initData() {
        mModels = new ArrayList<>();
        for (int i = 0; i < mData.length; i++) {
            MainModel model = new MainModel();
            model.setName(mData[i]);
            mModels.add(model);
        }
    }
}
