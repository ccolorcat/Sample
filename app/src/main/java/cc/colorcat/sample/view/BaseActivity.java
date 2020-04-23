package cc.colorcat.sample.view;

import android.content.Intent;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import cc.colorcat.sample.contact.IBase;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public abstract class BaseActivity extends AppCompatActivity implements IBase.View {
    @Override
    public boolean isActive() {
        return !this.isFinishing();
    }

    @Override
    public void toast(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void toast(@StringRes int resId) {
        toast(getText(resId));
    }

    @ColorInt
    protected int obtainColor(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getColor(resId);
        } else {
            return getResources().getColor(resId, getTheme());
        }
    }

    protected void navigateTo(Class<? extends BaseActivity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    protected void batchClick(View.OnClickListener click, @IdRes int... viewIds) {
        for (int id : viewIds) {
            findViewById(id).setOnClickListener(click);
        }
    }
}
