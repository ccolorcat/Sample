package cc.colorcat.sample.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import cc.colorcat.netbird.Level;
import cc.colorcat.netbird.Platform;
import cc.colorcat.sample.R;
import cc.colorcat.sample.api.ApiListener;
import cc.colorcat.sample.api.ApiService;
import cc.colorcat.sample.entity.Repo;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public class LauncherActivity extends BaseActivity {
    private EditText mUserEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mUserEt = findViewById(R.id.et_user);
        batchClick(mClick, R.id.btn_show_courses, R.id.btn_show_repos, R.id.btn_test);
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_show_courses:
                    navigateTo(CoursesActivity.class);
                    break;
                case R.id.btn_show_repos:
                    String user = mUserEt.getText().toString().trim();
                    if (TextUtils.isEmpty(user)) {
                        toast("user is empty");
                    } else {
                        ReposActivity.start(LauncherActivity.this, user);
                    }
                    break;
                case R.id.btn_test:
                    test();
                    break;
                default:
                    break;
            }
        }
    };

    private void test() {
        new ApiService().listRepos("ccolorcat").enqueue(new ApiListener<List<Repo>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<Repo> result) {
                Platform.get().logger().log("NetBird", result.toString(), Level.DEBUG);
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
