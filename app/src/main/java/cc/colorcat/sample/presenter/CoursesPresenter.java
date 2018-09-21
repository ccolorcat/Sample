package cc.colorcat.sample.presenter;

import java.util.List;

import cc.colorcat.sample.api.ApiListener;
import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class CoursesPresenter extends ListPresenter<Course> {

    @Override
    protected void realGetItems(boolean refresh, boolean more, ApiListener<List<Course>> listener) {
        mService.listCourses(4, 30).enqueue(listener);
    }
}