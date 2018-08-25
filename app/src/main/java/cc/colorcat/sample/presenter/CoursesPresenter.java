package cc.colorcat.sample.presenter;

import java.util.List;

import cc.colorcat.sample.api.WeakListener;
import cc.colorcat.sample.contact.IList;
import cc.colorcat.sample.entity.Course;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public class CoursesPresenter extends ListPresenter<Course> {

    @Override
    protected void realGetItems(final boolean refresh, final boolean more) {
        mService.listCourses(4, 30).enqueue(new WeakListener<IList.View<Course>, List<Course>>(mView) {
            @Override
            public void onStart(IList.View<Course> view) {
                super.onStart(view);
                view.setRefreshing(true);
            }

            @Override
            public void onSuccess(IList.View<Course> view, List<Course> data) {
                if (more) {
                    view.addMoreItems(data);
                } else {
                    view.refreshItems(data);
                }
            }

            @Override
            public void onFinish(IList.View<Course> view) {
                super.onFinish(view);
                view.setRefreshing(false);
            }
        });
    }
}