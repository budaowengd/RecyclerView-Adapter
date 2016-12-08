package com.luoxiong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.luoxiong.base.ItemTypeManager;
import com.luoxiong.base.IAdapterItemType;
import com.luoxiong.base.ViewHolder;

import java.util.List;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/12/8
 * 描    述：
 * ================================================
 */
public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;

    protected ItemTypeManager mItemTypeManager;
    protected OnItemClickListener mOnItemClickListener;

    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;
    public MultiItemTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        mItemTypeManager = new ItemTypeManager();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewTypeManager())
            return super.getItemViewType(position);
        return mItemTypeManager.getItemViewType(mDatas.get(position), position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IAdapterItemType itemViewType = mItemTypeManager.getItemType(viewType);
        int layoutId = itemViewType.getItemViewLayoutId();
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
        onViewHolderCreated(holder, holder.getConvertView());
        setListener(parent, holder, viewType);
        return holder;
    }

    public void onViewHolderCreated(ViewHolder holder, View itemView) {

    }

    public void convert(ViewHolder holder, T t) {
        mItemTypeManager.convert(holder, t, holder.getAdapterPosition());
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

//        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mOnItemClickListener != null) {
//                    int position = viewHolder.getAdapterPosition();
//                    return mOnItemClickListener.onItemLongClick(v, viewHolder, position);
//                }
//                return false;
//            }
//        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewType(IAdapterItemType<T> itemViewType) {
        mItemTypeManager.addIAdapterItem(itemViewType);
        return this;
    }

    public MultiItemTypeAdapter addItemViewType(int viewType, IAdapterItemType<T> itemViewType) {
        mItemTypeManager.addIAdapterItem(viewType, itemViewType);
        return this;
    }

    protected boolean useItemViewTypeManager() {
        return mItemTypeManager.getItemTypeSize() > 0;
    }

    //点击接口回调
    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
      //boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
