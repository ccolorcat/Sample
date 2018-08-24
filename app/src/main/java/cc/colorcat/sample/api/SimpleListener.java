package cc.colorcat.sample.api;

/**
 * Author: cxx
 * Date: 2018-08-24
 * GitHub: https://github.com/ccolorcat
 */
public abstract class SimpleListener<T> implements ApiListener<T> {
    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess(T result) {

    }

    @Override
    public void onFailure(int code, String msg) {

    }

    @Override
    public void onFinish() {

    }
}
