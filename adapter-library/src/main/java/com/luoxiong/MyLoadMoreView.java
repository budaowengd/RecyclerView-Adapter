package com.luoxiong;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * author:         luoxiong
 * creation date:  2016/12/14
 * desc ：
 */
public class MyLoadMoreView extends ImageView {

    private AnimationDrawable mAnim=null;

    public MyLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAnim = (AnimationDrawable) getBackground();
        mAnim.start();
    }
}
