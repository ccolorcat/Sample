package cc.colorcat.sample;

import android.app.Application;

import cc.colorcat.sample.api.BaseImpl;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class SampleClient extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseImpl.init(this, BuildConfig.BASE_URL, BuildConfig.DEBUG);
    }
}
