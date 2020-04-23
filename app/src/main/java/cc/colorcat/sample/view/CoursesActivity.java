package cc.colorcat.sample.view;

import java.util.List;

import cc.colorcat.adapter.AdapterHelper;
import cc.colorcat.adapter.RvAdapter;
import cc.colorcat.sample.R;
import cc.colorcat.sample.entity.Course;
import cc.colorcat.sample.presenter.CoursesPresenter;

public class CoursesActivity extends ListActivity<Course, CoursesPresenter> {
    @Override
    protected CoursesPresenter getPresenter() {
        return new CoursesPresenter();
    }

    @Override
    protected RvAdapter getRvAdapter(List<Course> items) {
        return AdapterHelper.newSimpleRvAdapter(
                items,
                new CourseViewBinder(4F, obtainColor(R.color.colorAccent))
        );
    }
}
