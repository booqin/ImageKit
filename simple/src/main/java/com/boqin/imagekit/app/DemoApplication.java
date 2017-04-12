package com.boqin.imagekit.app;

import com.boqin.xgimageview.NGFresco;
import com.boqin.xgimageview.config.DefultConfig;

import android.app.Application;

/**
 * TODO
 * Created by Boqin on 2017/4/10.
 * Modified by Boqin
 *
 * @Version
 */
public class DemoApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        NGFresco.initialize(this, DefultConfig.getImagePipelineConfig(this), DefultConfig.getDraweeViewConfig(this));
    }
}
