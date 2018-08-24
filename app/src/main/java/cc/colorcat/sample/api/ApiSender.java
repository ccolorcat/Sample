package cc.colorcat.sample.api;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public interface ApiSender<T> {
    ApiSender<T> setCacheControl(long maxAge);

    void get(ApiListener<T> listener);

    void head(ApiListener<T> listener);

    void trace(ApiListener<T> listener);

    void options(ApiListener<T> listener);

    void post(ApiListener<T> listener);

    void put(ApiListener<T> listener);

    void delete(ApiListener<T> listener);

    void cancel();
}
