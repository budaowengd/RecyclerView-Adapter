package com.luoxiong.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.luoxiong.base.IAdapterItemType;
import com.luoxiong.base.ViewHolder;

import java.util.List;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/12/7
 * 描    述：1、可以有空布局、可以有加载更多
 * ================================================
 */
public abstract class SimpleAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public SimpleAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewType(new IAdapterItemType<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                SimpleAdapter.this.convert(holder, t, position);

            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);
}
