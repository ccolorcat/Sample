package cc.colorcat.sample.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cc.colorcat.sample.R;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        batchClick(mClick, R.id.btn_show_courses, R.id.btn_show_repos);
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_show_courses:
                    navigateTo(CoursesActivity.class);
                    break;
                case R.id.btn_show_repos:
                    showReposView("ccolorcat");
                    break;
                default:
                    break;
            }
        }
    };

    private void showReposView(String user) {
        ReposActivity.start(this, user);
    }
}
