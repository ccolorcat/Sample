package cc.colorcat.sample.contact;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public interface IBase {
    interface View {
        boolean isActive();

        void toast(CharSequence text);

        void toast(@StringRes int resId);
    }

    interface Presenter<V extends View> {
        void onCreate(@NonNull V view);

        void onDestroy();
    }
}
