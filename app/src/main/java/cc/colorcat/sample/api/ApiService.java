package cc.colorcat.sample.api;

import java.util.List;

import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public final class ApiService implements Api {
    @Override
    public ApiSender<List<Course>> listCourses(int type, int number) {
        return new BaseService<List<Course>>() {}
                .path("api/teacher")
                .add("type", type)
                .add("num", number);
    }
}
