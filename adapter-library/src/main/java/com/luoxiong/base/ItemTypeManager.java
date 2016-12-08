package com.luoxiong.base;

import android.support.v4.util.SparseArrayCompat;


/**
 * Created by zhy on 16/6/22.
 *  ItemViewHolder管理
 *  每个适配器的管理ItemView
 */
public class ItemTypeManager<T> {
    SparseArrayCompat<IAdapterItemType<T>> itemTypeSize = new SparseArrayCompat();

    public int getItemTypeSize() {
        return itemTypeSize.size();
    }

    public ItemTypeManager<T> addIAdapterItem(IAdapterItemType<T> itemType) {
        int viewType = itemTypeSize.size();
        if (itemType != null) {
            itemTypeSize.put(viewType, itemType);
            viewType++;
        }
        return this;
    }

    public ItemTypeManager<T> addIAdapterItem(int viewType, IAdapterItemType<T> itemType) {
        if (itemTypeSize.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An IAdapterItemType is already registered for the viewType = "
                            + viewType
                            + ". Already registered IAdapterItemType is "
                            + itemTypeSize.get(viewType));
        }
        itemTypeSize.put(viewType, itemType);
        return this;
    }

    public ItemTypeManager<T> removeIAdapterItem(IAdapterItemType<T> itemType) {
        if (itemType == null) {
            throw new NullPointerException("IAdapterItemType is null");
        }
        int indexToRemove = itemTypeSize.indexOfValue(itemType);

        if (indexToRemove >= 0) {
            itemTypeSize.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemTypeManager<T> removeIAdapterItem(int itemType) {
        int indexToRemove = itemTypeSize.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            itemTypeSize.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position) {
        int delegatesCount = itemTypeSize.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            IAdapterItemType<T> itemType = itemTypeSize.valueAt(i);
            if (itemType.isForViewType(item, position)) {
                return itemTypeSize.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No IAdapterItemType added that matches position=" + position + " in data source");
    }

    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = itemTypeSize.size();
        for (int i = 0; i < delegatesCount; i++) {
            IAdapterItemType<T> itemType = itemTypeSize.valueAt(i);

            if (itemType.isForViewType(item, position)) {
                itemType.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemTypeManager added that matches position=" + position + " in data source");
    }


    public IAdapterItemType getItemType(int viewType) {
        return itemTypeSize.get(viewType);
    }

    public int getItemViewLayoutId(int viewType) {
        return getItemType(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(IAdapterItemType itemViewIAdapterItem) {
        return itemTypeSize.indexOfValue(itemViewIAdapterItem);
    }
}
