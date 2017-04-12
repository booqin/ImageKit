package com.boqin.image_kit;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;

import android.graphics.drawable.Drawable;

/**
 * 视图配置文档
 * Created by Boqin on 2017/4/11.
 * Modified by Boqin
 *
 * @Version
 */
public class DraweeViewConfig {

    /** 占位符drawable */
    private Drawable mPlaceholderDrawable;
    private Drawable mProgressDrawable;

    private DraweeViewConfig(Buidler buidler){
        mPlaceholderDrawable = buidler.mPlaceholderDrawable;
        mProgressDrawable = buidler.mProgressDrawable;
    }


    public static Buidler newBuidler(){
        return new Buidler();
    }

    public Drawable getPlaceholderDrawable() {
        return mPlaceholderDrawable;
    }

    public void setPlaceholderDrawable(Drawable placeholderDrawable) {
        mPlaceholderDrawable = placeholderDrawable;
    }

    public Drawable getProgressDrawable() {
        return mProgressDrawable;
    }

    public void setProgressDrawable(Drawable progressDrawable) {
        mProgressDrawable = progressDrawable;
    }

    public GenericDraweeHierarchyBuilder setGenericDraweeHierarchy(GenericDraweeHierarchyBuilder builder) {
        if (mProgressDrawable!=null) {
            builder.setProgressBarImage(mProgressDrawable);
        }
        if(mPlaceholderDrawable!=null){
            builder.setPlaceholderImage(mPlaceholderDrawable);
        }
        return builder;
    }

    public static class Buidler{

        /** 占位符drawable */
        private Drawable mPlaceholderDrawable;
        private Drawable mProgressDrawable;


        private Buidler(){

        }

        public DraweeViewConfig bulder(){
            return new DraweeViewConfig(this);
        }

        public Buidler setPlaceholderDrawable(Drawable placeholderDrawable){
            mPlaceholderDrawable = placeholderDrawable;
            return this;
        }

        public Buidler setProgressDrawable(Drawable progressDrawable){
            mProgressDrawable = progressDrawable;
            return this;
        }

    }

}
