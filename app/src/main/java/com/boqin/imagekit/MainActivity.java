package com.boqin.imagekit;

import com.boqin.xgimageview.view.NGDraweeView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String uri = "http://img3.duitang.com/uploads/item/201410/24/20141024180639_UzJKG.gif";


    private NGDraweeView mNGDraweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        mNGDraweeView = (NGDraweeView) findViewById(R.id.dv_image);
        mNGDraweeView.setLoadingImage(uri);
    }
}
