package cc.colorcat.sample.api;

import java.util.List;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public interface Api {
    interface Base<T> {
        Base<T> setCacheControl(long maxAge);

        void send(MRequest.Listener<T> listener);

        void cancel();
    }

    interface GetCourses extends Base<List<Course>> {
        String PATH = "api/teacher";

        GetCourses get(int type, int number);
    }
}
