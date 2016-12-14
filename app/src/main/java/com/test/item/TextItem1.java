package com.test.item;

import android.support.v7.widget.RecyclerView;

import com.luoxiong.base.IAdapterItem;
import com.luoxiong.base.ViewHolder;
import com.luoxiong.tools.Logs;
import com.test.R;
import com.test.domain.ModelData;

/**
 * author:         luoxiong
 * creation date:  2016/12/12
 * desc ：
 */
public class TextItem1 implements IAdapterItem<ModelData> {
    @Override
    public int getItemViewLayoutId(RecyclerView.Adapter adapter) {
        return R.layout.item_text1;
    }

    @Override
    public void convert(ViewHolder holder, ModelData bean, int position) {
        Logs.d("convert。。。。"+position);
        holder.setText(R.id.tv_name, bean.content+"   "+position);
    }
}
