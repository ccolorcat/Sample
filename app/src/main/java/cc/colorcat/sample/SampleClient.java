package cc.colorcat.sample;

import android.app.Application;

import cc.colorcat.sample.api.ApiEngine;
import cc.colorcat.vangogh.VanGogh;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class SampleClient extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiEngine.init(this, BuildConfig.BASE_URL, BuildConfig.DEBUG);
        VanGogh.setSingleton(new VanGogh.Builder(this).indicator(true).build());
    }
}
