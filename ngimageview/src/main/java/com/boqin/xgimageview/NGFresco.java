package com.boqin.xgimageview;

import com.boqin.xgimageview.config.DefultConfig;
import com.boqin.xgimageview.config.DraweeViewConfig;
import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import android.content.Context;

/**
 * 图片工具
 * Created by Boqin on 2017/4/6.
 * Modified by Boqin
 *
 * @Version
 */
public class NGFresco {

    private static DraweeViewConfig mDraweeViewConfig;

    /**
     * 采用Fresco的默认初始化
     * @param context
     */
    public static void initialize(Context context){
        Fresco.initialize(context);
    }

    /**
     * 使用默认配置设置初始化，包括缓存设置和默认图设置等
     * 默认配置:{@link DefultConfig}
     * @param context
     */
    public static void initializeDefult(Context context){
        initialize(context, DefultConfig.getImagePipelineConfig(context), DefultConfig.getDraweeViewConfig(context));
    }

    /**
     * @param context
     * @param imagePipelineConfig 图片加载设置，可以使用默认配置{@link DefultConfig#getImagePipelineConfig(Context)}
     */
    public static void initialize(Context context, ImagePipelineConfig imagePipelineConfig){
        Fresco.initialize(context, imagePipelineConfig);
    }
    /**
     * @param context
     * @param draweeViewConfig 图片加载设置，可以使用默认配置{@link DefultConfig#getDraweeViewConfig(Context)}
     */
    public static void initialize(Context context, DraweeViewConfig draweeViewConfig){
        Fresco.initialize(context);
        mDraweeViewConfig = draweeViewConfig;
    }

    /**
     * @param context
     * @param imagePipelineConfig {@link #initialize(Context, ImagePipelineConfig)}
     * @param draweeViewConfig {@link #initialize(Context, DraweeViewConfig)}
     */
    public static void initialize(Context context, ImagePipelineConfig imagePipelineConfig, DraweeViewConfig draweeViewConfig){
        Fresco.initialize(context, imagePipelineConfig);
        mDraweeViewConfig = draweeViewConfig;
    }

    public static void initialize(Context context, ImagePipelineConfig imagePipelineConfig, DraweeViewConfig draweeViewConfig, DraweeConfig draweeConfig){
        Fresco.initialize(context, imagePipelineConfig, draweeConfig);
        mDraweeViewConfig = draweeViewConfig;
    }

    /**
     * 获取默认视图配置
     */
    public static DraweeViewConfig getDraweeViewConfig() {
        return mDraweeViewConfig;
    }
}
