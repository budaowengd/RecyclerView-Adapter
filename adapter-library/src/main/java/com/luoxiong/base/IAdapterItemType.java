package com.luoxiong.base;


/**
 * Created by zhy on 16/6/22.
 * ItemViewHolder管理
 */
public interface IAdapterItemType<T> {

    int getItemViewLayoutId();



    void convert(ViewHolder holder, T t, int position);

}
