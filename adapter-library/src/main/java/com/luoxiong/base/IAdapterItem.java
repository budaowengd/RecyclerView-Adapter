package com.luoxiong.base;


import android.support.v7.widget.RecyclerView;

/**
 * Created by zhy on 16/6/22.
 * ItemViewHolder管理
 */
public interface IAdapterItem<T> {

    int getItemViewLayoutId(RecyclerView.Adapter adapter);

    void convert(ViewHolder holder, T t, int position);
}
