package com.boqin.imagekit.app;

import com.facebook.drawee.backends.pipeline.Fresco;

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

        Fresco.initialize(this);
    }
}
