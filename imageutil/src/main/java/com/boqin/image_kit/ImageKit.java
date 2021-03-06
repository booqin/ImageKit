package com.boqin.image_kit;

import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import android.content.Context;

/**
 * 图片工具包
 * Created by Boqin on 2017/4/6.
 * Modified by Boqin
 *
 * @Version
 */
public class ImageKit {

    private static DraweeViewConfig mDraweeViewConfig;

    public static void initialize(Context context){
        Fresco.initialize(context);
    }
    public static void initialize(Context context, ImagePipelineConfig imagePipelineConfig){
        Fresco.initialize(context, imagePipelineConfig);
    }

    public static void initialize(Context context, DraweeViewConfig draweeViewConfig){
        Fresco.initialize(context);
        mDraweeViewConfig = draweeViewConfig;
    }

    public static void initialize(Context context, ImagePipelineConfig imagePipelineConfig, DraweeViewConfig draweeViewConfig){
        Fresco.initialize(context, imagePipelineConfig);
        mDraweeViewConfig = draweeViewConfig;
    }
    public static void initialize(Context context, ImagePipelineConfig imagePipelineConfig, DraweeViewConfig draweeViewConfig, DraweeConfig draweeConfig){
        Fresco.initialize(context, imagePipelineConfig, draweeConfig);
        mDraweeViewConfig = draweeViewConfig;
    }

    public static DraweeViewConfig getDraweeViewConfig() {
        return mDraweeViewConfig;
    }
}
