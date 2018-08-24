package cc.colorcat.sample.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cc.colorcat.adapter.RvAdapter;
import cc.colorcat.adapter.RvHolder;
import cc.colorcat.adapter.SimpleRvAdapter;
import cc.colorcat.sample.R;
import cc.colorcat.sample.contact.ICourse;
import cc.colorcat.sample.entity.Course;
import cc.colorcat.sample.presenter.CoursesPresenter;
import cc.colorcat.vangogh.CircleTransformation;
import cc.colorcat.vangogh.CornerTransformation;
import cc.colorcat.vangogh.OvalTransformation;
import cc.colorcat.vangogh.Transformation;
import cc.colorcat.vangogh.VanGogh;

public class MainActivity extends BaseActivity implements ICourse.View {
    private ICourse.Presenter mPresenter = new CoursesPresenter();
    private SwipeRefreshLayout mRefreshLayout;
    private List<Course> mCourses = new ArrayList<>(30);
    private RvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rv_courses);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SimpleRvAdapter<Course>(mCourses, R.layout.item_course) {
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
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRefreshLayout = findViewById(R.id.srl_root);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.toRefreshCourses();
            }
        });
        mPresenter.onCreate(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void refreshCourses(List<Course> courses) {
        mCourses.clear();
        mCourses.addAll(courses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mRefreshLayout.setRefreshing(refreshing);
    }
}
