package com.luoxiong.base;

/**
 * ================================================
 * 作    者：罗雄
 * 创建日期：2016/12/25
 * 描    述：
 * ================================================
 */
//public class SwipeRefreshRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
//    private Context mContext;
//    private RecyclerView mRecyclerView;
//    private SwipeRefreshLayout mRefreshLayout;
//    public SwipeRefreshRecyclerView(Context context) {
//        super(context);
//    }
//
//    public SwipeRefreshRecyclerView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        mContext = context;
//        init();
//    }
//
//    private void init() {
//        View v = LayoutInflater.from(mContext).inflate(R.layout.base_swiperefresh_recy, this, false);
//        mRecyclerView= (RecyclerView) v.findViewById(R.id.id_RecyclerView);
//        mRefreshLayout= (SwipeRefreshLayout) v.findViewById(R.id.id_SwipeRefreshLayout);
//
//        mRefreshLayout.setColorSchemeResources(R.color.c1);
//        mRefreshLayout.setOnRefreshListener(this);
//        //设置右侧滚动条可见
//        mRecyclerView.setVerticalScrollBarEnabled(true);
//        //提高效率
//        mRecyclerView.setHasFixedSize(true);
//        addView(v);
//    }
//    public void setLayoutManager(){
//
//    }
//
//    @Override
//    public void onRefresh() {
//
//    }
//}
