package com.example.benben.animation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.benben.animation.R;

import butterknife.ButterKnife;

/**
 * Created by benben on 2016/4/22.
 * RecyclerView的适配器
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private String[] datas = null;
    private OnItemClickListener mListener;

    public MainAdapter(String[] datas) {
        this.datas = datas;
    }

    /**
     * 创建view
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_main, parent, false);
        ViewHolder mHolder = new ViewHolder(view);
        return mHolder;
    }

    /**
     * 绑定ViewHolder
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTextView.setText(datas[position]);

        /**设置监听*/
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();//得到当前点击item的位置
                    mListener.ItemClickListener(holder.itemView, pos);//把事情交给实现的接口来处理
                }
            });

        }

    }

    /**
     * 获取数据的数量
     */
    @Override
    public int getItemCount() {
        return datas.length;
    }


    /**
     * 自定义的ViewHolder，持有每个Item的所有界面元素
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.timeTextView);
        }
    }

    /**添加点击事件*/
  public interface OnItemClickListener{
        void ItemClickListener(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


}
