package com.luoxiong.tools;

import android.util.SparseArray;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/10/29
 * 描    述：
 * ================================================
 */
public class ItemTypeUtil {
    /**
     * 存放Holder类型
     */
    private SparseArray<Object> typeSArr = new SparseArray<>();

    /**
     * type: text、button、ImageView
     */
    public int getIntType(Object type) {
        int index = typeSArr.indexOfValue(type);
        if (index == -1) {
            index = typeSArr.size();
            // 如果没用这个type，就存入这个type
            typeSArr.put(index, type);
        }
        return index;
    }
}
