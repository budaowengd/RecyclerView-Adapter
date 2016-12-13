package com.test;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.luoxiong.tools.Logs;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context mContext;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    MyAdapter mAdapter;
    List<String> mTitles = new ArrayList<>();
    List<Fragment> mFragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mTabLayout= (TabLayout) findViewById(R.id.id_TabLayout);
        mViewPager= (ViewPager) findViewById(R.id.id_ViewPager);
        initFrag();
    }


    private void initFrag() {
        mTitles.add("有下拉有加载更多");
        mTitles.add("有下拉无加载更多");
        mTitles.add("无下拉无加载更多");

        mFragments.add(new Frag1());
        mFragments.add(new Frag2());
        mFragments.add(new Frag3());


        mAdapter = new MyAdapter(getSupportFragmentManager());
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mAdapter);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

    }
    @Override
    public void onDestroy() {
        Logs.d("MainActivity。。。。onDestroy()。。222222。。");
        super.onDestroy();
    }

}
