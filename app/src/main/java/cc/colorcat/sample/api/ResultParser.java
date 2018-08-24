package cc.colorcat.sample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;

import cc.colorcat.netbird.NetworkData;
import cc.colorcat.netbird.Parser;
import cc.colorcat.netbird.Response;
import cc.colorcat.netbird.StateIOException;
import cc.colorcat.sample.entity.Result;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class ResultParser<T> implements Parser<T> {
    private static final Gson GSON = new GsonBuilder().create();

    public static <T> ResultParser<T> create(TypeToken<Result<T>> token) {
        if (token == null) throw new NullPointerException("token == null");
        return new ResultParser<>(token);
    }

    private final TypeToken<Result<T>> mToken;

    private ResultParser(TypeToken<Result<T>> token) {
        mToken = token;
    }

    @Override
    public NetworkData<? extends T> parse(Response response) throws IOException {
        try {
            Reader reader = response.responseBody().reader();
            Result<T> result = GSON.fromJson(reader, mToken.getType());
            if (result != null) {
                T data = result.getData();
                if (result.getStatus() == Result.STATUS_OK && data != null) {
                    return NetworkData.newSuccess(data);
                }
                return NetworkData.newFailure(result.getStatus(), result.getMsg());
            }
            return NetworkData.newFailure(response.responseCode(), response.responseMsg());
        } catch (Exception e) {
            throw new StateIOException(response.responseCode(), response.responseMsg(), e);
        }
    }
}
