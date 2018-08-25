package cc.colorcat.sample.api;

import java.io.IOException;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public interface ApiSender<T> {
    ApiSender<T> setCacheControl(long maxAge);

    T execute() throws IOException;

    void enqueue(ApiListener<T> listener);

    void cancel();
}
