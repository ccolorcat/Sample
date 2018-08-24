package cc.colorcat.sample.api;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.netbird.Method;
import cc.colorcat.netbird.cache.CacheControl;
import cc.colorcat.sample.entity.Result;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public abstract class BaseService<T> implements ApiSender<T> {
    private MRequest.Builder<T> mBuilder;
    private Object mTag;

    @SuppressWarnings("unchecked")
    BaseService() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        TypeToken<Result<T>> token = (TypeToken<Result<T>>) TypeToken.getParameterized(Result.class, pt.getActualTypeArguments());
        mBuilder = new MRequest.Builder<>(ResultParser.create(token));
    }

    public final BaseService<T> url(String url) {
        mBuilder.url(url);
        return this;
    }

    public final BaseService<T> path(String path) {
        mBuilder.path(path);
        return this;
    }

    public final BaseService<T> add(String name, String value) {
        mBuilder.add(name, value);
        return this;
    }

    public final BaseService<T> add(String name, int value) {
        mBuilder.add(name, Integer.toString(value));
        return this;
    }

    public final BaseService<T> add(String name, double value) {
        mBuilder.add(name, Double.toString(value));
        return this;
    }

    public final BaseService<T> add(String name, float value) {
        mBuilder.add(name, Float.toString(value));
        return this;
    }

    @Override
    public final ApiSender<T> setCacheControl(long maxAge) {
        mBuilder.setHeader(CacheControl.HEADER_NAME_MAX_AGE, Long.toString(maxAge));
        return this;
    }

    @Override
    public final void get(ApiListener<T> listener) {
        send(Method.GET, listener);
    }

    @Override
    public final void head(ApiListener<T> listener) {
        send(Method.HEAD, listener);
    }

    @Override
    public final void trace(ApiListener<T> listener) {
        send(Method.TRACE, listener);
    }

    @Override
    public final void options(ApiListener<T> listener) {
        send(Method.OPTIONS, listener);
    }

    @Override
    public final void post(ApiListener<T> listener) {
        send(Method.POST, listener);
    }

    @Override
    public final void put(ApiListener<T> listener) {
        send(Method.PUT, listener);
    }

    @Override
    public final void delete(ApiListener<T> listener) {
        send(Method.DELETE, listener);
    }

    @Override
    public final void cancel() {
        ApiEngine.cancel(mTag);
    }

    private void send(Method method, ApiListener<T> listener) {
        mTag = ApiEngine.sendRequest(mBuilder.method(method).listener(listener).build());
    }
}
