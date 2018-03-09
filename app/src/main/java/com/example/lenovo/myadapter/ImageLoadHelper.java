package com.example.lenovo.myadapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by lenovo on 2018/3/9.
 */

public class ImageLoadHelper {
    private static ImageLoadHelper instance;
    private ImageLoadHelper(){}
    public static synchronized ImageLoadHelper getInstance(){
        if (instance == null) {
            instance = new ImageLoadHelper();
        }
        return instance;
    }
    /**
     * 通过路径加载图片
     * @param path
     * @param view
     */
    public  void loadImgPath( Object path, ImageView view){
        Glide.with(MyApplication.getInstance()).load(path).centerCrop().into(view);
    }

}
