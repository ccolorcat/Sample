package cc.colorcat.sample.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.IOException;
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
            builder.addTailInterceptor(new LoggingTailInterceptor(false) {
                private Gson gson = new GsonBuilder().setPrettyPrinting().create();
                private JsonParser parser = new JsonParser();

                @Override
                protected String formatResponse(String content, String contentType) {
//                    return contentType;
                    return '\n' + gson.toJson(parser.parse(content));
                }
            });
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

    public static <T> T execute(MRequest<T> request) throws IOException {
        return netBird.execute(request);
    }

    public static void cancel(Object tag) {
        netBird.cancelWaiting(tag);
    }
}
