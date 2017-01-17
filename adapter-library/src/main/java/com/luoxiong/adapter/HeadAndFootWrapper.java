package com.luoxiong.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.luoxiong.base.ViewHolder;
import com.luoxiong.tools.WrapperUtils;

/**
 * todo 弃用
 */
public class HeadAndFootWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEAD_BASE_TYPE = 100000;
    private static final int FOOT_BASE_TYPE = 200000;

    private RecyclerView.Adapter mInnerAdapter;
    private View mHeadView;
    private View mFootView;

    public HeadAndFootWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (getHeadView()) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeadView);
            return holder;

        } else if (getFootView()) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootView);
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    private boolean getHeadView() {
        return mHeadView != null;
    }

    private boolean getFootView() {
        return mFootView != null;
    }

    @Override
    public int getItemViewType(int position) {
        if (getHeadView()) {
            return HEAD_BASE_TYPE;
        } else if (getFootView()) {
            return FOOT_BASE_TYPE;
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getHeadView()) {
            return;
        }
        if (getFootView()) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup
                    oldLookup, int position) {
                if (getHeadView()) {
                    return layoutManager.getSpanCount();
                } else if (getFootView()) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null)
                    return oldLookup.getSpanSize(position);
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            WrapperUtils.setFullSpan(holder);
        }
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }


    public void addHeaderView(View view) {
         this.mHeadView=view;
    }

    public void addFootView(View view) {
        this.mFootView=view;
    }

    public int getHeadersCount() {
        return getHeadView() ? 1 : 0;
    }

    public int getFootersCount() {
        return getFootView() ? 1 : 0;
    }
}
