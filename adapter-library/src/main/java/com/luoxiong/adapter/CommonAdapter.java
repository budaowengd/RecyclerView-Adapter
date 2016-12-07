package com.luoxiong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/12/7
 * 描    述：1、可以有空布局、可以有加载更多
 * ================================================
 */
public class CommonAdapter<T>  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
