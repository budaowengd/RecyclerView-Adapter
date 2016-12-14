package com.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.luoxiong.adapter.EmptyTypeWrapper;
import com.luoxiong.adapter.MultiItemTypeAdapter;
import com.luoxiong.base.IAdapterItem;
import com.luoxiong.tools.Logs;
import com.test.domain.ModelData;
import com.test.item.TextItem1;
import com.test.item.TextItem2;
import com.test.item.TextItem3;
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
public class Frag1 extends Fragment implements View.OnClickListener {

    RecyclerView mRecyclerView;
    Frag12Adapter mAdapter;
    List<ModelData> mDatas = new ArrayList<>();
    int sum = 20;
    private View mView;
    private GridLayoutManager mGridManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment1, container, false);
        mView.findViewById(R.id.add).setOnClickListener(this);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_RecyclerView);
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mGridManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mGridManager);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatas.addAll(DataManager.loadModelData(1));
        //mAdapter=new Frag1Adapter(getContext(),R.layout.item_text1,mDatas);
        mAdapter = new Frag12Adapter(getContext(), mDatas);
        TextView headView = new TextView(getContext());
        headView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        headView.setBackgroundColor(0xffff0000);
        headView.setText("I am header");
        mAdapter.setHeadView(headView);

        mRecyclerView.setAdapter(mAdapter);
        initEvent();
    }

    private void initEvent() {
        mAdapter.setOnLoadMoreListener(new MultiItemTypeAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<ModelData> modelDatas = DataManager.loadModelData(10);
                        mAdapter.addDataAll(modelDatas, true);
//                        mRecyclerView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                mAdapter.setLoadMoreState(mAdapter.FAIL_STATE);
//                                mAdapter.notifyItemChanged(mAdapter.getItemCount() - 1);
//                            }
//                        }, 1500);
                    }
                }, 1000);
            }
        });
    }

    protected void setEmpty() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.clear();
                mAdapter.setHeadView(null);
                //空布局
                EmptyTypeWrapper adapter = new EmptyTypeWrapper(mAdapter);
                TextView emptyView = new TextView(getContext());
                emptyView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                emptyView.setBackgroundColor(0x33ff0000);
                emptyView.setText("I am Empty");
                adapter.setEmptyView(emptyView);
                mRecyclerView.setAdapter(adapter);
            }
        }, 3000);
    }


    class Frag12Adapter extends MultiItemTypeAdapter<ModelData> {
        public Frag12Adapter(Context context, List<ModelData> datas) {
            super(context, datas);
        }

//        @Override
//        public String getItemType(ModelData bean) {
//                return bean.type;
//        }

        @Override
        public IAdapterItem createItem(String type) {
            if (type != null) {
                if (type.equals("text")) {
                    return new TextItem1();
                } else if (type.equals("button")) {
                    return new TextItem2();
                } else if (type.equals("image")) {
                    return new TextItem3();
                }
            }
            return new TextItem1();
        }
    }

    int spanCount = 1;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            spanCount = spanCount == 1 ? 2 : 1;
            Logs.d("spanCount: "+spanCount);
            mGridManager.setSpanCount(spanCount);
            mAdapter.notifyDataSetChanged();
        }
    }

}
