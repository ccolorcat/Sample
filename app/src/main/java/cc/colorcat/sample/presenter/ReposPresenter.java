package cc.colorcat.sample.presenter;

import android.support.annotation.NonNull;

import cc.colorcat.sample.contact.IList;
import cc.colorcat.sample.entity.Repo;
import cc.colorcat.sample.view.ReposActivity;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public class ReposPresenter extends ListPresenter<Repo> {
    private String mUser;

    @Override
    public void onCreate(@NonNull IList.View<Repo> view) {
        mUser = view.getExtra(ReposActivity.USER);
        super.onCreate(view);
    }

    @Override
    protected void realGetItems(boolean refresh, boolean more) {
        if (!more) {
            mService.listRepos(mUser).enqueue(getWeakListener(refresh, false));
        }
    }
}
