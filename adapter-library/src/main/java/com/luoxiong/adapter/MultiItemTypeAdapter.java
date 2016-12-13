package com.luoxiong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.luoxiong.base.IAdapterItem;
import com.luoxiong.base.ItemTypeManager;
import com.luoxiong.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/12/8
 * 描    述：
 * ================================================
 */
public abstract class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected List<T> mDatas=new ArrayList<>();;

    protected ItemTypeManager mItemTypeManager;
    protected OnItemClickListener mOnItemClickListener;

    private View mLoadMoreView;
    /**
     * holder类型
     */
    private String mHolderType;
    private static final int HEAD_BASE_TYPE = 100000;
    private static final int FOOT_BASE_TYPE = 200000;
    private static final int LOAD_MORE_BASE_TYPE = 300000;
    private View mHeadView;
    private View mFootView;


    public MultiItemTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas.addAll(datas);
        mItemTypeManager = new ItemTypeManager();
    }

    public abstract IAdapterItem createItem(String type);

    @Override
    public int getItemViewType(int position) {
        if (getItemType(null).equals(null)) {
            return super.getItemViewType(position);
        } else if (getHeadView()) {
            return HEAD_BASE_TYPE;
        } else if (getFootView()) {
            return FOOT_BASE_TYPE;
        }
        //有 text、button、img等类型,假如分别对应 1 2 3
        mHolderType = getItemType(mDatas.get(position)); //mHolderType有 text、button、img等类型
        return mItemTypeManager.getItemViewType(mHolderType);
    }

    public String getItemType(T t) {
        return null; // default,有 text、button、img
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建ViewHolder
        IAdapterItem item = createItem(mHolderType);
        ViewHolder holder = ViewHolder.createViewHolder(parent, item, this);
        onViewHolderCreated(holder, holder.getConvertView());
        setListener(parent, holder, viewType);
        return holder;
    }

    public void onViewHolderCreated(ViewHolder holder, View itemView) {
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getItem().convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        int count = getListSize();
        int offset = 0;
        if (getHeadView()) {
            offset++;
        }
        if (getFootView()) {
            offset++;
        }

        if (hasLoadMore()) {
            offset++;
        }
        // LogUtils.d("1111。。。getItemCount()。。。。"+(offset + count)+"  count:"+count+"  offset："+offset);
        return offset + count;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    //================ 添加头、添加脚=================== start..
    private boolean getHeadView() {
        return mHeadView != null;
    }

    private boolean getFootView() {
        return mFootView != null;
    }

    public void addHeaderView(View view) {
        this.mHeadView = view;
    }

    public void addFootView(View view) {
        this.mFootView = view;
    }

    public int getHeadersCount() {
        return getHeadView() ? 1 : 0;
    }

    public int getFootersCount() {
        return getFootView() ? 1 : 0;
    }
    //================ 添加头、和添加脚=================== end

    /**
     * 获取是否支持加载更多
     */
    public boolean hasLoadMore() {
        return mLoadMoreListener != null && getListSize() > 0;
    }
    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= getItemCount());
    }

    public int getListSize() {
         return mDatas.size();
    }

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
    }
}
