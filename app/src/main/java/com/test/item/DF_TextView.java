package com.test.item;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * 首页轮播图下面，Tab指示器本身的文字描述
 * <p>
 * 指示器下面的产品总数量 也是当前TextView
 */
public class DF_TextView extends TextView {

    private int typeface = 0;
    private final int NORMAL = 0;
    private final int BOLD = 1;
    private Typeface font;

    public DF_TextView(Context context) {
        super(context);
        init(context, null);
    }

    public DF_TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    boolean flag = false;

    private void init(Context context, AttributeSet attrs) {


        if (flag) {
            font = Typeface.createFromAsset(context.getAssets(),
                    "fonts/centurygothic.ttf");
        } else {
           // setA5(context);
          //this.setTypeface(font);
            //setTypeface(Typeface.MONOSPACE);
        }
    }

    private void setA1(Context context) {
        font = Typeface.createFromAsset(context.getAssets(),
                //"fonts/centuryb.ttf");
                "fonts/Roboto-Light.ttf");
    }

    private void setA2(Context context) {
        font = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Thin.ttf");
    }

    private void setA3(Context context) {
        font = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Medium.ttf");
    }

    private void setA4(Context context) {
        font = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Black.ttf");
    }

    private void setA5(Context context) {
        font = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
    }
  private void setA6(Context context) {
        font = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
    }


}
