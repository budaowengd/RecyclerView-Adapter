package com.luoxiong.tools;


/**
 * author:         luoxiong
 * creation date:  2016/12/14
 * desc ：
 */
public class LoadMoreDataManager<T> {
//    /**
//     * 根据pageSize 判断是否支持加载更多
//     *
//     * pageSize如果是20，loadData大小如果为19，则说明没有数据了，不显示加载更多
//     */
//    public void addDatas(List<T> loadData, boolean isLoadMore) {
//        if (!isLoadMore) {
//            mDatas.clear();
//        }
//        if (loadData != null && !loadData.isEmpty()) {
//            this.mLoadDataSize = loadData.size();
//            List<T> dataList = loadData;
//            if (pageSize > 0) {
//                //如果大于加载的集合大小，说明没有更多数据了
//                if (pageSize != dataList.size()) {
//                    mLoadMoreEnable = false;
//                } else {
//                    this.mDefaultPageSize = pageSize;
//                    mLoadMoreEnable = true;
//                }
//            } else {
//                // mLoadMoreEnable = false;
//            }
//            LogUtils.d("addDatas22。。。size。" + dataList.size() + "  pageSize：" + pageSize + " mLoadMoreEnable:" + mLoadMoreEnable);
//            mDatas.addAll(dataList);
//            notifyChange(isLoadMore);
//        } else {
//            mRecyParent.setRefreshing(false);
//        }
//    }
}
