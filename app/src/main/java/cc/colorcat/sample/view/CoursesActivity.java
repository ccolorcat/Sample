package cc.colorcat.sample.view;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.util.List;

import cc.colorcat.adapter.RvAdapter;
import cc.colorcat.adapter.RvHolder;
import cc.colorcat.adapter.SimpleRvAdapter;
import cc.colorcat.sample.R;
import cc.colorcat.sample.entity.Course;
import cc.colorcat.sample.presenter.CoursesPresenter;
import cc.colorcat.vangogh.CircleTransformation;
import cc.colorcat.vangogh.Transformation;
import cc.colorcat.vangogh.VanGogh;

public class CoursesActivity extends ListActivity<Course, CoursesPresenter> {
    @Override
    protected CoursesPresenter getPresenter() {
        return new CoursesPresenter();
    }

    @Override
    protected RvAdapter getRvAdapter(List<Course> items) {
        return new SimpleRvAdapter<Course>(items, R.layout.item_course) {
            private Transformation transformation = new CircleTransformation(
                    4, obtainColor(R.color.colorAccent)
            );

            @Override
            public void bindView(@NonNull RvHolder holder, @NonNull Course data) {
                RvHolder.Helper helper = holder.getHelper();
                VanGogh.get()
                        .load(data.getPicSmall())
                        .addTransformation(transformation)
                        .into((ImageView) helper.get(R.id.iv_icon));
                helper.setText(R.id.tv_serial_number, Integer.toString(holder.getAdapterPosition()))
                        .setText(R.id.tv_name, data.getName())
                        .setText(R.id.tv_description, data.getDescription());
            }
        };
    }
}
