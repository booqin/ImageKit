package com.boqin.xgimageview.view;

import com.boqin.xgimageview.NGFresco;
import com.boqin.xgimageview.config.DraweeViewConfig;
import com.boqin.xgimageview.util.ScreenUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import android.Manifest;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 图片加载控件
 * Created by Boqin on 2017/4/7.
 * Modified by Boqin
 *
 * @Version
 */
public class NGDraweeView extends SimpleDraweeView {
    /** 记录请求url,用于个别界面需要取出比较 */
    private String mUrl;
    private boolean mIsCircle = false;

    public NGDraweeView(Context context) {
        super(context);
        init();
    }

    public NGDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NGDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 获取当前加载图片的url
     *
     * @return mUrl
     */
    public String getUrl() {
        return mUrl;
    }

    /** 设置占位（默认）图片的res id。如果不想要默认图片，将resId设为0 */
    public void setPlaceholderImage(
            @DrawableRes
                    int resId) {
        if (resId > 0) {
            GenericDraweeHierarchy hierarchy = this.getHierarchy();
            if (hierarchy != null) {
                hierarchy.setPlaceholderImage(resId);
                setHierarchy(hierarchy);
            }
        } else {
            setPlaceholderImage(null);
        }
    }

    /** 设置占位（默认）图片。如果不想要默认图片，传null */
    public void setPlaceholderImage(Drawable drawable) {
        GenericDraweeHierarchy hierarchy = this.getHierarchy();
        if (hierarchy != null) {
            hierarchy.setPlaceholderImage(drawable);
            setHierarchy(hierarchy);
        }
    }

    public RoundingParams getRoundingParams() {
        GenericDraweeHierarchy h = this.getHierarchy();

        if (h != null) {
            return h.getRoundingParams();
        }

        return null;
    }

    public void setRoundingParams(RoundingParams rp) {
        GenericDraweeHierarchy h = this.getHierarchy();
        h.setRoundingParams(rp);
        this.setHierarchy(h);
    }

    /**
     * 设置图片带有圆角
     */
    public void setIsCircle(final boolean isCircle) {
        getHierarchy().setRoundingParams(new RoundingParams() {
            @Override
            public boolean getRoundAsCircle() {
                mIsCircle = isCircle;
                return mIsCircle;
            }
        });
        GenericDraweeHierarchy hierarchy = getHierarchy();
    }

    /**
     *
     */
    public void setActualImageScaleType(ScalingUtils.ScaleType scaleType) {
        getHierarchy().setActualImageScaleType(scaleType);
    }

    /**
     * 加载网络图片。
     * 根据该LoadingImageView的长宽去取（Urs上的）图片。所以该View必须是尺寸确定的。
     * <p>如果需要手动改变宽高，你需要调用{@link #setLoadingImage(String, int, int)}
     * <p>如果需要自适应图片宽高，可以通过{@link #setLoadingImageAjustment(String)}
     *
     * @param url 图片链接地址
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public void setLoadingImage(final String url) {
        mUrl = url;
        if (TextUtils.isEmpty(url)) {
            setURIToController(null);
            return;
        }

        setURIToController(url);
    }

    /**
     * 加载已知尺寸的网络图片。
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public void setLoadingImage(final String url, int dipWidth, int dipHeight) {
        mUrl = url;
        if (TextUtils.isEmpty(url)) {
            setURIToController(null);
            return;
        }
        setURIToController(url, dipWidth, dipHeight);
    }

    /**
     * 加载已知尺寸的网络图片。
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public void setLoadingImageAjustment(final String url) {
        mUrl = url;
        if (TextUtils.isEmpty(url)) {
            setURIToController(null);
            return;
        }
        setURIToControllerAjust(url);
    }

    /**
     * 清除url对应的磁盘缓存以及内存缓存，重新加载图片
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public void loadImageAndEvictCache(String url) {
        Uri uri = Uri.parse(url);

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.evictFromMemoryCache(uri);
        imagePipeline.evictFromDiskCache(uri);

        setImageURI(uri);
    }

    /**
     * 清除url对应的磁盘缓存以及内存缓存，重新加载图片
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public void loadImageAndEvictCache(Uri uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.evictFromMemoryCache(uri);
        imagePipeline.evictFromDiskCache(uri);

        setImageURI(uri);
    }

    /**
     * 把图片下载到磁盘缓存，但是不显示出来
     */
    public void downloadToDisCache(String url) {
        Uri uri = Uri.parse(url.trim());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).build();
        Fresco.getImagePipeline().prefetchToDiskCache(request, this.getContext().getApplicationContext());

    }

    /**
     * 初始化设置
     */
    private void init() {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        //加载默认配置
        DraweeViewConfig draweeViewConfig = NGFresco.getDraweeViewConfig();
        draweeViewConfig.setGenericDraweeHierarchy(builder);
        GenericDraweeHierarchy h = builder.build();
        this.setHierarchy(h);
    }


    private void setURIToController(String uriString) {
        Uri uri = Uri.parse(uriString);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .setOldController(this.getController()) //复用减少不必要的内存分配
                .build();
        this.setController(controller);
    }

    private void setURIToController(String uriString, int dipW, int dipH) {
        Uri uri = Uri.parse(uriString);
        final ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        layoutParams.width = dipW;
        layoutParams.height = dipH;
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .setOldController(this.getController()) //复用减少不必要的内存分配
                .build();
        this.setController(controller);
    }

    private void setURIToControllerAjust(String uriString) {
        Uri uri = Uri.parse(uriString);
        final ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id,
                    @Nullable
                            ImageInfo imageInfo,
                    @Nullable
                            Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                //设置宽高比
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                //判断图片宽度是否大于屏幕宽
                layoutParams.width = width > ScreenUtil.getScreenWidth(NGDraweeView.this.getContext()) ? ScreenUtil
                        .getScreenWidth(NGDraweeView.this.getContext()) : width;

                //按比例设置
                layoutParams.height = (int) ((float) (layoutParams.width * height) / (float) width);
                //设置布局参数
                NGDraweeView.this.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id,
                    @Nullable
                            ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        //网络请求图片，重设大小加载到内存
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(ScreenUtil.getScreenWidth(this.getContext()),
                        ScreenUtil.getScreenHeight(this.getContext())))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .setOldController(this.getController()) //复用减少不必要的内存分配
                .setControllerListener(controllerListener)
                .build();
        this.setController(controller);
    }
}
