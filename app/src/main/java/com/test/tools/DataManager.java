package com.test.tools;

import com.test.R;
import com.test.domain.ModelData;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 作    者：罗雄
 * 创建日期：2016/11/13
 * 描    述：
 * ================================================
 */
public class DataManager {

    /**
     * 模拟加载数据的操作
     */
    public static List<ModelData> loadModelData(int num) {
        List<ModelData> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int type = (int) (Math.random() * 3);
             type = 1;
            ModelData model = new ModelData();
            switch (type) {
                case 1:
                    model.type = "text";
                    model.content = "My is text";
                    break;
                case 2:
                    model.type = "button";
                    model.content = "My is button";
                    break;
                case 3:
                    model.type = "image";
                    model.content = String.valueOf(R.mipmap.ic_launcher);
                    break;
                default:
            }
            list.add(model);
        }
        return list;
    }
}
