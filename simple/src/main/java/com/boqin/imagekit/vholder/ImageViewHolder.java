package com.boqin.imagekit.vholder;

import com.boqin.imagekit.R;
import com.boqin.xgimageview.view.NGDraweeView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * TODO
 * Created by Boqin on 2017/4/10.
 * Modified by Boqin
 *
 * @Version
 */
public class ImageViewHolder extends RecyclerView.ViewHolder{

    private NGDraweeView mDraweeView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        mDraweeView = (NGDraweeView) itemView.findViewById(R.id.dv_image);
    }

    public void rend(String uri){
        mDraweeView.setLoadingImageAjustment(uri);
    }

}
