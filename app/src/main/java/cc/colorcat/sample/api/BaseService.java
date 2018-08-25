package cc.colorcat.sample.api;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cc.colorcat.netbird.MRequest;
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

    protected BaseService() {
        mBuilder = createBuilder();
    }

    protected MRequest.Builder<T> createBuilder() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] types = pt.getActualTypeArguments();
        @SuppressWarnings("unchecked")
        TypeToken<Result<T>> token = (TypeToken<Result<T>>) TypeToken.getParameterized(Result.class, types);
        return new MRequest.Builder<>(ResultParser.create(token));
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

    public final BaseService<T> get() {
        mBuilder.get();
        return this;
    }


    public final BaseService<T> head() {
        mBuilder.head();
        return this;
    }


    public final BaseService<T> trace() {
        mBuilder.trace();
        return this;
    }


    public final BaseService<T> options() {
        mBuilder.options();
        return this;
    }


    public final BaseService<T> post() {
        mBuilder.post();
        return this;
    }


    public final BaseService<T> put() {
        mBuilder.put();
        return this;
    }


    public final BaseService<T> delete() {
        mBuilder.delete();
        return this;
    }


    @Override
    public final ApiSender<T> setCacheControl(long maxAge) {
        mBuilder.setHeader(CacheControl.HEADER_NAME_MAX_AGE, Long.toString(maxAge));
        return this;
    }

    @Override
    public final T execute() throws IOException {
        MRequest<T> request = mBuilder.build();
        mTag = request.tag();
        return ApiEngine.execute(request);
    }

    @Override
    public final void enqueue(ApiListener<T> listener) {
        mTag = ApiEngine.sendRequest(mBuilder.listener(listener).build());
    }

    @Override
    public final void cancel() {
        ApiEngine.cancel(mTag);
    }
}
