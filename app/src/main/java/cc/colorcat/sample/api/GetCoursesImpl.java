package cc.colorcat.sample.api;

import java.util.List;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class GetCoursesImpl extends BaseImpl<List<Course>> implements Api.GetCourses {
    private int type;
    private int number;

    @Override
    public Api.GetCourses get(int type, int number) {
        this.type = type;
        this.number = number;
        return this;
    }

    @Override
    protected MRequest.Builder<List<Course>> builder() {
        return create()
                .path(Api.GetCourses.PATH)
                .add("type", Integer.toString(type))
                .add("num", Integer.toString(number))
                .get();
    }
}
