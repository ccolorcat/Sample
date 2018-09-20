package cc.colorcat.sample.api2;

import java.lang.reflect.ParameterizedType;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.netbird.cache.CacheControl;
import cc.colorcat.sample.api.ApiEngine;
import cc.colorcat.sample.api.ApiListener;
import cc.colorcat.sample.api.ResultParser;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public abstract class BaseImpl<T> implements Api2.Base<T> {
    private long maxAge = CacheControl.MAX_AGE_NO_CACHE;
    private Object tag;

    @Override
    public Api2.Base<T> setCacheControl(long maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    @Override
    public void send(ApiListener<T> listener) {
        MRequest<T> request = builder()
                .setHeader(CacheControl.HEADER_NAME_MAX_AGE, Long.toString(maxAge))
                .listener(listener)
                .build();
        tag = ApiEngine.sendRequest(request);
    }

    @Override
    public void cancel() {
        ApiEngine.cancel(tag);
    }

    protected abstract MRequest.Builder<T> builder();

    @SuppressWarnings("unchecked")
    protected MRequest.Builder<T> create() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        return new MRequest.Builder<>(ResultParser.<T>create(pt.getActualTypeArguments()));
    }
}
