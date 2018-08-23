package cc.colorcat.sample.contact;

import java.util.List;

import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public interface ICourse {
    interface View extends IBase.View {
        void refreshCourses(List<Course> courses);

        void setRefreshing(boolean refreshing);
    }

    interface Presenter extends IBase.Presenter<View> {
        void doGetCourses();

        void toRefreshCourses();
    }
}
