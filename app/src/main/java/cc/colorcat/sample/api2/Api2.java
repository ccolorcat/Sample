package cc.colorcat.sample.api2;

import java.util.List;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.sample.api.ApiListener;
import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public interface Api2 {
    interface Base<T> {
        Base<T> setCacheControl(long maxAge);

        void send(ApiListener<T> listener);

        void cancel();
    }


    interface ListCourses extends Base<List<Course>> {
        String PATH = "api/teacher";

        ListCourses get(int type, int number);
    }
}
