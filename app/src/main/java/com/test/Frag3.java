package com.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.domain.ModelData;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：lx
 * 创建日期：2016/11/8
 * 描    述：有下拉有加载更多
 * ================================================
 */
public class Frag3 extends Fragment  {


    RecyclerView mRecyclerView;
    //Frag3Adapter mAdapter;
    List<ModelData> mDatas = new ArrayList<>();
    int sum=20;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.id_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mDatas.addAll(DataManager.loadModelData(sum));
//        mAdapter=new Frag3Adapter(getContext(),R.layout.item_text3,mDatas);
//        mRecyclerView.setAdapter(mAdapter);
//    }

//    class Frag3Adapter extends SimpleAdapter<ModelData> {
//
//        public Frag3Adapter(Context context, int layoutId, List<ModelData> datas) {
//            super(context, layoutId, datas);
//        }
//
//        @Override
//        protected void convert(ViewHolder holder, ModelData bean, int position) {
//            holder.setText(R.id.tv_name,bean.content+" = "+position);
//        }
//    }
}
