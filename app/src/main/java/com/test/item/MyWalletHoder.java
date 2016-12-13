package com.test.item;//package com.zhy.sample.adapter.rv;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.romwe.R;
//import com.romwe.lx.activity.WithDrawalUI2;
//import com.romwe.lx.baseap.interfac.IAdapterHolder;
//import com.romwe.module.me.bean.WalletInfo_Bean;
//import com.romwe.module.me.wallet.WalletActivity;
//import com.romwe.util.LogUtils;
//
//
///**
// * ================================================
// * 作    者：罗雄
// * 创建日期：2016/11/5
// * 描    述：我的钱包 余额列表
// * ================================================
// */
//public class MyWalletHoder implements IAdapterHolder<String> {
//    TextView mTvMoney;
//    Button mBtWallet;
//    private WalletActivity.MyWalletAdapter mAdapter;
//    Context mContext;

//    @Override
//    public int getLayoutResId(RecyclerView.Adapter adapter) {
//        if(mAdapter==null){
//            if(adapter instanceof WalletActivity.MyWalletAdapter){
//                mAdapter= (WalletActivity.MyWalletAdapter) adapter;
//            }
//        }
//        return R.layout.item_my_wallet_balance;
//    }
//
//    @Override
//    public void bindViews(View root,int position) {
//        if(mContext==null){
//            mContext=root.getContext();
//        }
//        mTvMoney= (TextView) root.findViewById(R.id.tv_money);
//        mBtWallet= (Button) root.findViewById(R.id.bt_withdraw);
//    }
//
//    @Override
//    public void setViews(RecyclerView.ViewHolder holder) {
//
//    }
//
//    @Override
//    public void handleData(String bean, int position) {
//        WalletInfo_Bean.WalletInfoDao dao = mAdapter.getMaps().get(bean);
//        mTvMoney.setText(dao.symbol_amount);
//        mBtWallet.setOnClickListener(new MyListener(position,bean));
//    }
//
//    class MyListener implements View.OnClickListener {
//        int postion;
//        String bean;
//        public MyListener(int pos,String ket){
//            this.postion=pos;
//            this.bean=ket;
//        }
//        @Override
//        public void onClick(View v) {
//            LogUtils.d("点击按钮："+postion);
//        WalletInfo_Bean.WalletInfoDao wlletInfoDao = mAdapter.getMaps().get(bean);
//        if (wlletInfoDao != null) {
//            String amount = wlletInfoDao.amount;
//            double result = parseDouble(amount);
//            if (result <= 0) {
//                //  没有钱提现
//            }
//            //MyApp.putToTransfer("WalletInfoDao",wlletInfoDao);
//            //Intent intent = new Intent(mContext, WithDrawalActivity.class);
//            Intent intent = new Intent(mContext, WithDrawalUI2.class);
//            intent.putExtra("currency",bean);
//            intent.putExtra("amount",amount);
//            intent.putExtra("symbol_amount",wlletInfoDao.symbol_amount);
//            if(mContext instanceof Activity){
//                Activity aa= (Activity) mContext;
//                aa.startActivityForResult(intent,100);
//            }
//
//
//            LogUtils.d("跳转去提现页面 货币是："+bean+"  余额："+amount);
//        }
//        }
//        private double parseDouble(String value) {
//            double result = 0;
//            if (value != null) {
//                value = value.replaceAll(" ", "");
//                try {
//                    result = Double.parseDouble(value);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return result;
//        }
//    }
//
//}
