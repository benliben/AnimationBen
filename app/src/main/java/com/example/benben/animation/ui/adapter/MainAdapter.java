package com.example.benben.animation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.benben.animation.R;

/**
 * Created by benben on 2016/4/25.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private String[] mDatas = null;
    private OnItemClickListener mListener;

    public MainAdapter(String[] mDatas) {
        this.mDatas = mDatas;
    }


    /**创建ViewHolder*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    /**绑定ViewHolder*/
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(mDatas[position]);

        /**设置监听*/
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();//得到当前点击item的位置
                    mListener.ItemClickListener(holder.itemView, pos);//把事情交给实现类的接口去实现
                }
            });
        }
    }


    /**获取数据的数量*/
    @Override
    public int getItemCount() {
        return mDatas.length;
    }


    /**自定义的VIewHolder，持有每给Item的所有界面的元素*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_main_text);
        }
    }

    /**添加点击事件*/
    public  interface OnItemClickListener{
        void ItemClickListener(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
