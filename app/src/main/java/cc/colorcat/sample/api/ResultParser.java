package cc.colorcat.sample.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

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
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> ResultParser<T> create(Type[] actualTypeArguments) {
        if (actualTypeArguments == null) {
            throw new NullPointerException("actualTypeArguments == null");
        }
        return new ResultParser<>(actualTypeArguments);
    }

    private final Type[] mActualTypeArguments;

    private ResultParser(Type[] actualTypeArguments) {
        mActualTypeArguments = actualTypeArguments;
    }

    @Override
    public NetworkData<? extends T> parse(Response response) throws IOException {
        try {
            // gson
            Reader reader = response.responseBody().reader();
            Type type = TypeToken.getParameterized(Result.class, mActualTypeArguments).getType();
            Result<T> result = GSON.fromJson(reader, type);

            // fastjson
//            String content = response.responseBody().string();
//            ParameterizedTypeImpl pt = new ParameterizedTypeImpl(mActualTypeArguments, null, Result.class);
//            Result<T> result = JSON.parseObject(content, pt);

            // jackson
//            Reader reader = response.responseBody().reader();
//            TypeFactory factory = TypeFactory.defaultInstance();
//            JavaType innerType = factory.constructType(mActualTypeArguments[0]);
//            JavaType outerType = factory.constructParametricType(Result.class, innerType);
//            Result<T> result = MAPPER.readValue(reader, outerType);

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
