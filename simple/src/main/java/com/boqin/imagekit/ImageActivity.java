package com.boqin.imagekit;

import java.util.ArrayList;
import java.util.List;

import com.boqin.imagekit.adapter.ImageAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * TODO
 * Created by Boqin on 2017/4/10.
 * Modified by Boqin
 *
 * @Version
 */
public class ImageActivity extends AppCompatActivity{

    private static final String[] URIS = new String[]{"http://img3.imgtn.bdimg.com/it/u=226060586,1322532223&fm=214&gp=0.jpg",
                                                      "http://img3.imgtn.bdimg.com/it/u=303661474,1291423175&fm=23&gp=0.jpg",
                                                      "http://img1.ph.126.net/U2RZGghq0IXO2F2nQxljIA==/2726648099413555131.jpg",
                                                      "http://img3.duitang.com/uploads/item/201510/30/20151030221315_aSUns.thumb.700_0.jpeg",
                                                      "http://img3.duitang.com/uploads/item/201410/24/20141024180639_UzJKG.gif"};

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(
            @Nullable
                    Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        List<String> stringList = new ArrayList<>();
        for (String uri : URIS) {
            stringList.add(uri);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_image);
        mAdapter = new ImageAdapter(stringList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }


}
