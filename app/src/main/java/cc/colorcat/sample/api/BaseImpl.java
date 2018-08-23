package cc.colorcat.sample.api;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;

import cc.colorcat.netbird.Interceptor;
import cc.colorcat.netbird.Level;
import cc.colorcat.netbird.MRequest;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.android.AndroidPlatform;
import cc.colorcat.netbird.cache.CacheControl;
import cc.colorcat.netbird.logging.LoggingTailInterceptor;
import cc.colorcat.sample.AndroidCacheInterceptor;
import cc.colorcat.sample.ResultParser;
import cc.colorcat.sample.entity.Result;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public abstract class BaseImpl<T> implements Api.Base<T> {
    private static NetBird netBird;

    public static void init(Context context, String baseUrl, boolean debug) {
        NetBird.Builder builder = new NetBird.Builder(baseUrl)
                .platform(new AndroidPlatform())
                .connectTimeOut(10000)
                .readTimeOut(10000)
                .enableGzip(true)
                .logLevel(debug ? Level.VERBOSE : Level.NOTHING);
        if (debug) {
            builder.addTailInterceptor(new LoggingTailInterceptor(true));
        }
        Interceptor cacheInterceptor = AndroidCacheInterceptor.create(context, Collections.<String>emptyList());
        if (cacheInterceptor != null) {
            builder.addTailInterceptor(cacheInterceptor);
        }
        BaseImpl.netBird = builder.build();
    }

    private Object tag;
    private long maxAge = CacheControl.MAX_AGE_NO_CACHE;

    @Override
    public Api.Base<T> setCacheControl(long maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    @Override
    public void send(MRequest.Listener<T> listener) {
        MRequest<T> request = builder()
                .setHeader(CacheControl.HEADER_NAME_MAX_AGE, Long.toString(maxAge))
                .listener(listener)
                .build();
        this.tag = request.tag();
        BaseImpl.netBird.send(request);
    }

    @Override
    public void cancel() {
        BaseImpl.netBird.cancelWaiting(this.tag);
    }

    protected abstract MRequest.Builder<T> builder();

    @SuppressWarnings("unchecked")
    protected MRequest.Builder<T> create() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        TypeToken<Result<T>> token = (TypeToken<Result<T>>) TypeToken.getParameterized(Result.class, pt.getActualTypeArguments());
        return new MRequest.Builder<>(ResultParser.create(token));
    }
}
