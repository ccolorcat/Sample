package cc.colorcat.sample.presenter;

import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class CoursesPresenter extends ListPresenter<Course> {

    @Override
    protected void realGetItems(final boolean refresh, final boolean more) {
        if (!more) {
            mService.listCourses(4, 30).enqueue(getWeakListener(refresh, false));
        }
    }
}