package com.boqin.imagekit.adapter;

import java.util.ArrayList;
import java.util.List;

import com.boqin.imagekit.R;
import com.boqin.imagekit.vholder.ImageViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * TODO
 * Created by Boqin on 2017/4/10.
 * Modified by Boqin
 *
 * @Version
 */
public class ImageAdapter extends RecyclerView.Adapter{

    private List<String> mUriList;

    public ImageAdapter(List<String> uriList){
        mUriList = new ArrayList<>();
        mUriList.addAll(uriList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ImageViewHolder)holder).rend(mUriList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUriList.size();
    }

}
