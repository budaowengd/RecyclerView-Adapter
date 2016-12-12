package com.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luoxiong.adapter.MultiItemTypeAdapter;
import com.luoxiong.base.IAdapterItem;
import com.test.domain.ModelData;
import com.test.item.TextItem1;
import com.test.tools.DataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/11/8
 * 描    述：有下拉有加载更多
 * ================================================
 */
public class Frag1 extends Fragment {

    RecyclerView mRecyclerView;
    Frag12Adapter mAdapter;
    List<ModelData> mDatas = new ArrayList<>();
    int sum = 20;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatas.addAll(DataManager.loadModelData(sum));
        //mAdapter=new Frag1Adapter(getContext(),R.layout.item_text1,mDatas);
        mAdapter = new Frag12Adapter(getContext(), mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

//    class Frag1Adapter extends SimpleAdapter<ModelData> {
//
//        public Frag1Adapter(Context context, int layoutId, List<ModelData> datas) {
//            super(context, layoutId, datas);
//        }
//
//        @Override
//        protected void convert(ViewHolder holder, ModelData bean, int position) {
//            holder.setText(R.id.tv_name,bean.content+" = "+position);
//        }
//
//        @Override
//        public IAdapterItem createItem(Object type) {
//            return null;
//        }
//    }


    class Frag12Adapter extends MultiItemTypeAdapter<ModelData> {
        public Frag12Adapter(Context context, List<ModelData> datas) {
            super(context, datas);
        }

//        @Override
//        public String getItemType(ModelData bean) {
//            return bean.type;
//        }

        @Override
        public IAdapterItem createItem(String type) {
            //throw new IllegalArgumentException("不合法的type");
            if (type != null) {
                if (type.equals("text")) {
                    return new TextItem1();
                } else if (type.equals("button")) {
                    return new TextItem1();
                } else if (type.equals("image")) {
                    return new TextItem1();
                }
            }
            return new TextItem1();
        }
    }
}
