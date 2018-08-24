package cc.colorcat.sample.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import cc.colorcat.sample.api.WeakListener;
import cc.colorcat.sample.contact.ICourse;
import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class CoursesPresenter extends BasePresenter<ICourse.View> implements ICourse.Presenter {

    @Override
    public void onCreate(@NonNull ICourse.View view) {
        super.onCreate(view);
        doGetCourses();
    }

    @Override
    public void doGetCourses() {
        loadCourses();
    }

    @Override
    public void toRefreshCourses() {
        loadCourses();
    }

    private void loadCourses() {
        mService.listCourses(4, 30)
                .get(new WeakListener<ICourse.View, List<Course>>(mView) {
                    @Override
                    public void onStart(ICourse.View view) {
                        super.onStart(view);
                        view.setRefreshing(true);
                    }

                    @Override
                    public void onSuccess(ICourse.View view, List<Course> data) {
                        view.refreshCourses(data);
                    }

                    @Override
                    public void onFinish(ICourse.View view) {
                        super.onFinish(view);
                        view.setRefreshing(false);
                    }
                });
    }
}
