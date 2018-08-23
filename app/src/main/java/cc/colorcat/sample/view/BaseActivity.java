package cc.colorcat.sample.view;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
}
