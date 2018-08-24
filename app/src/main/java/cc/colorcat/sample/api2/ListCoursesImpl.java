package cc.colorcat.sample.api2;

import java.util.List;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class ListCoursesImpl extends BaseImpl<List<Course>> implements Api2.ListCourses {
    private int type;
    private int number;

    @Override
    public Api2.ListCourses get(int type, int number) {
        this.type = type;
        this.number = number;
        return this;
    }

    @Override
    protected MRequest.Builder<List<Course>> builder() {
        return create()
                .path(Api2.ListCourses.PATH)
                .add("type", Integer.toString(type))
                .add("num", Integer.toString(number))
                .get();
    }
}
