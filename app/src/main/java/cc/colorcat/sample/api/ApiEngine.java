package cc.colorcat.sample.api;

import android.content.Context;

import java.util.Collections;

import cc.colorcat.netbird.Interceptor;
import cc.colorcat.netbird.Level;
import cc.colorcat.netbird.MRequest;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.android.AndroidPlatform;
import cc.colorcat.netbird.logging.LoggingTailInterceptor;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public final class ApiEngine {
    private static NetBird netBird;

    public static void init(Context context, String baseUrl, boolean debug) {
        if (netBird != null) throw new IllegalStateException("initialized");
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
        netBird = builder.build();
    }

    public static <T> Object sendRequest(MRequest<T> request) {
        return netBird.send(request);
    }

    public static void cancel(Object tag) {
        netBird.cancelWaiting(tag);
    }
}
