package cc.colorcat.sample.presenter;

import android.support.annotation.NonNull;

import cc.colorcat.sample.api.ApiService;
import cc.colorcat.sample.contact.IBase;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class BasePresenter<V extends IBase.View> implements IBase.Presenter<V> {
    static final ApiService mService = new ApiService();
    V mView;

    @Override
    public void onCreate(@NonNull V view) {
        mView = view;
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
