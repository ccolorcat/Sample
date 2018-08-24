package cc.colorcat.sample.api;

import android.support.annotation.CallSuper;
import android.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

import cc.colorcat.netbird.HttpStatus;
import cc.colorcat.netbird.MRequest;
import cc.colorcat.sample.R;
import cc.colorcat.sample.api.ApiListener;
import cc.colorcat.sample.contact.IBase;


/**
 * Created by cxx on 2018/4/23
 * xx.ch@outlook.com
 */
public abstract class WeakListener<V extends IBase.View, T> extends SimpleListener<T> {
    private Reference<V> ref;

    protected WeakListener(V v) {
        this.ref = new WeakReference<>(v);
    }

    @Override
    public final void onStart() {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onStart(v);
        }
    }

    @Override
    public final void onSuccess(T data) {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onSuccess(v, data);
        }
    }

    @Override
    public final void onFailure(int code, String msg) {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onFailure(v, code, msg);
        }
    }

    @Override
    public final void onFinish() {
        V v = ref.get();
        if (v != null && v.isActive()) {
            onFinish(v);
            ref.clear();
        }
    }

    public void onStart(V view) {

    }

    public abstract void onSuccess(V view, T data);

    @CallSuper
    public void onFailure(V view, int code, String msg) {
        switch (code) {
            case HttpStatus.CODE_CONNECT_ERROR:
                view.toast(R.string.common_net_error);
                break;
            case HttpURLConnection.HTTP_OK:
                view.toast(msg);
                break;
            default:
                Log.e("NetBird", "api failure, code=" + code + ", msg=" + msg);
                break;
        }
    }

    public void onFinish(V view) {
    }
}

