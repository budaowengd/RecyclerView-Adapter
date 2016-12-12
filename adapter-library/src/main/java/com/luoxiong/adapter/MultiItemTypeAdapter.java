package com.luoxiong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
    protected Context mContext;
    protected List<T> mDatas;

    protected ItemTypeManager mItemTypeManager;
    protected OnItemClickListener mOnItemClickListener;

    /**
     * holder类型
     */
    private String mHolderType;
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

    public MultiItemTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        mItemTypeManager = new ItemTypeManager();

    }

    public abstract IAdapterItem createItem(String type);


    @Override
    public int getItemViewType(int position) {
//        if (!useItemViewTypeManager())
//            return super.getItemViewType(position);
        //有 text、button、img等类型,假如分别对应 1 2 3
        //return mItemTypeManager.getItemViewType(mDatas.get(position), position);
        return getMyItemType(position);
    }

    public int getMyItemType(int position) {
        mHolderType = getItemType(mDatas.get(position)); //mHolderType有 text、button、img等类型
        return mItemTypeManager.getItemViewType(mHolderType);
    }

    public String getItemType(T t) {
        return null; // default,有 text、button、img
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取item类型
        //IAdapterItem itemViewType = mItemTypeManager.getItemType(viewType);

        //通过item类型获取id
        // int layoutId = itemViewType.getItemViewLayoutId();
        //创建ViewHolder
        IAdapterItem item = createItem(mHolderType);

        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, item);
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
        //mItemTypeManager.convert(holder, t, holder.getAdapterPosition());
//        IAdapterItem item = (IAdapterItem) holder.getConvertView();
//        item.convert(holder,mDatas.get(position),position);


    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }

    public List<T> getDatas() {
        return mDatas;
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
