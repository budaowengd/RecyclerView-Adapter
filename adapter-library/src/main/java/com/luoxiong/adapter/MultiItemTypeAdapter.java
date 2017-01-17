package com.luoxiong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luoxiong.R;
import com.luoxiong.base.IAdapterItem;
import com.luoxiong.base.ItemTypeManager;
import com.luoxiong.base.ViewHolder;

import java.util.List;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/12/8
 * 描    述：
 * ================================================
 */
public abstract class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private static final int HEAD_BASE_TYPE = 100000;
    private static final int NORMAL_BASE_TYPE = 200000;
    private static final int FOOT_BASE_TYPE = 300000;
    private static final int LOAD_MORE_BASE_TYPE = 400000;
    public static final String LOADING_STATE = "loading_state";
    public static final String FAIL_STATE = "fail_state";
    public static final String NO_MORE_STATE = "no_more_state";

    protected Context mContext;
    protected List<T> mDatas;

    protected ItemTypeManager mItemTypeManager;
    protected OnItemClickListener mOnItemClickListener;

    /**
     * holder类型
     */
    private String mHolderType;

    //头脚要在一起，因为分开的话，那么加载更多也要分开
    private View mHeadView;
    private View mFootView;
    //加载更多
    private View mLoadMoreView;
    private String mLoadMoreState = LOADING_STATE;
    private int mPageSize = 20;
    private int mLoadDataSize;
    private boolean mLoadMoreEnable;
    private boolean isLoadMoreing = false;
    private RecyclerView mRecyclerView;

    public MultiItemTypeAdapter(Context context, List<T> dataList, RecyclerView rView) {
        this.mContext = context;
        this.mDatas = dataList;
        this.mItemTypeManager = new ItemTypeManager();
        this.mRecyclerView = rView;
        // mRecyclerView.addOnScrollListener(new SimpleRecyScrollListener());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD_BASE_TYPE:
                return ViewHolder.createViewHolder(parent.getContext(), mHeadView);
            case FOOT_BASE_TYPE:
                return ViewHolder.createViewHolder(parent.getContext(), mFootView);
            case LOAD_MORE_BASE_TYPE:
                mLoadMoreView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loadmore_layout, parent, false);
                return ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            default:
                //创建默认ViewHolder
                IAdapterItem item = createItem(mHolderType);
                ViewHolder holder = ViewHolder.createViewHolder(parent, item, this);
                onViewHolderCreated(holder, holder.getConvertView());
                setListener(holder, viewType);
                return holder;
        }
    }


    public void onViewHolderCreated(ViewHolder holder, View itemView) {
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    public void setListener(final ViewHolder holder, int viewType) {
        if (!isEnabled(viewType)) return;
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, holder, position);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case HEAD_BASE_TYPE:
                break;
            case FOOT_BASE_TYPE:
                break;
            case LOAD_MORE_BASE_TYPE:
                initLoadMore(holder);
                break;
            default:
                holder.getItem().convert(holder, mDatas.get(getMinusHeadPos(position)),
                        getMinusHeadPos(position));
        }
    }

    private void initLoadMore(ViewHolder holder) {
        if (TextUtils.equals(mLoadMoreState, LOADING_STATE)) {
            //加载中
            showLoading(holder);
        } else if (TextUtils.equals(mLoadMoreState, FAIL_STATE)) {
            //失败，会显示refresh文字，点击可以重新加载
            showLoadFail(holder, false);
        } else {
            //显示没有更多数据
            showLoadFail(holder, true);
        }
    }

    /**
     * 显示加载中的布局
     */
    public void showLoading(ViewHolder holder) {
        isLoadMoreing = true;
        holder.setVisible(R.id.lx_loading_ll, true);
        holder.setVisible(R.id.lx_load_fail_fl, false);
        mLoadMoreListener.onLoadMore();
    }

    /**
     * 显示加载失败的布局
     */
    public void showLoadFail(final ViewHolder holder, boolean isNoMore) {
        holder.setVisible(R.id.lx_load_fail_fl, true);
        holder.setVisible(R.id.lx_loading_ll, false);
        if (isNoMore) {
            holder.setVisible(R.id.lx_tv_load_fail, false);
            holder.setVisible(R.id.lx_tv_no_more, true);
        } else {
            holder.setVisible(R.id.lx_tv_load_fail, true);
            holder.setVisible(R.id.lx_tv_no_more, false);
            holder.setOnClickListener(R.id.lx_tv_load_fail, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLoading(holder);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getHeadView() && isHeaderViewPos(position)) {
            return HEAD_BASE_TYPE;
        } else if (getFootView() && isFooterViewPos(position)) {
            return FOOT_BASE_TYPE;
        } else if (isShowLoadMore(position)) {
            return LOAD_MORE_BASE_TYPE;
        } else {
            T t = mDatas.get(getMinusHeadPos(position));
            if (getItemType(t) == null) {
                return NORMAL_BASE_TYPE;
            }
            //有 text、button、img等类型,假如分别对应 1 2 3
            mHolderType = getItemType(t); //mHolderType有 text、button、img等类型
            return mItemTypeManager.getItemViewType(mHolderType);
        }
    }

    protected String getItemType(T t) {
        return null; // default,有 text、button、img
    }

    @Override
    public int getItemCount() {
        int count = getListSize();
        if (getHeadView()) {
            count++;
        }
        if (getFootView()) {
            count++;
        }
        if (hasLoadMore()) {
            count++;
        }
        // LogUtils.d("1111。。。getItemCount()。。。。"+(offset + count)+"  count:"+count+"  offset："+offset);
        return count;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
//                    return getItemViewType(position) == HEAD_BASE_TYPE ||
//                            getItemViewType(position) == FOOT_BASE_TYPE
//                            || getItemViewType(position) == LOAD_MORE_BASE_TYPE
//                            ? gridManager.getSpanCount() : 1;
                    return getItemViewType(position) == NORMAL_BASE_TYPE ? 1 : gridManager.getSpanCount();

                }
            });
        }
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public int getListSize() {
        return mDatas.size();
    }

    //================ 添加头、添加脚=================== start..
    public int getMinusHeadPos(int pos) {
        return pos - getHeadCount();
    }

    public int getPlusHeadPos(int pos) {
        return pos + getHeadCount();
    }

    private boolean getHeadView() {
        return mHeadView != null;
    }

    private boolean getFootView() {
        return mFootView != null;
    }

    public void setHeadView(View view) {
        this.mHeadView = view;
    }

    public void setFootView(View view) {
        this.mFootView = view;
    }

    public int getHeadCount() {
        return getHeadView() ? 1 : 0;
    }

    public int getFootCount() {
        return getFootView() ? 1 : 0;
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getFootCount() + getListSize();
    }

    //================ 添加头、和添加脚=================== end


    //================ 加载更多=================== start
    public boolean hasLoadMore() {
        //return mLoadMoreEnable && getListSize() > 0&&(mDy>0||mDx>0);
        return mLoadMoreEnable && getListSize() > 0;
    }

    //-1是因为loadMore也作为一个holder+1了
    private boolean isShowLoadMore(int position) {
        boolean isShow = hasLoadMore() && (position >= getItemCount() - 1);
        return isShow;
    }

    /**
     * 创建加载更多的布局
     */

    public void setLoadMoreView(@NonNull View view, @NonNull View loadingContain, @NonNull View loadFailContain) {
        this.mLoadMoreView = view;
    }

    public void setLoadMoreState(String loadMoreState) {
        mLoadMoreState = loadMoreState;

    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }

    public void addDataAll(List<T> loadData, boolean isLoadMore) {
        if (!isLoadMore) {
            mDatas.clear();
        }
        this.mLoadDataSize = loadData.size();
        mDatas.addAll(loadData);
        //如果是加载更多
        if (isLoadMore) {
            isLoadMoreing = false;
            //还有数据
            if (mPageSize > mLoadDataSize) {
                //没有数据
                mLoadMoreState = NO_MORE_STATE;
            }
            notifyItemRangeChanged(getListSize() - mLoadDataSize + getHeadCount()
                    , mLoadDataSize + getFootCount() + 1);
        } else {
            notifyDataSetChanged();
        }
    }

    private int mDx, mDy;

    class SimpleRecyScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mDx = dx;
            mDy = dy;
        }
    }

    //================ 加载更多=================== end

    /**
     * 点击接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
        //boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 加载更多接口回调
     */

    private OnLoadMoreListener mLoadMoreListener;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener l) {
        this.mLoadMoreListener = l;
        mLoadMoreEnable = true;
    }

    public abstract IAdapterItem createItem(String type);
}
