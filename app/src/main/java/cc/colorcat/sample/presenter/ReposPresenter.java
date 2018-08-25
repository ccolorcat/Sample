package cc.colorcat.sample.presenter;

import android.support.annotation.NonNull;

import cc.colorcat.sample.contact.IList;
import cc.colorcat.sample.contact.IRepos;
import cc.colorcat.sample.entity.Repo;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public class ReposPresenter extends ListPresenter<Repo> implements IRepos.Presenter {
    private String mUser;

    @Override
    public void onCreate(@NonNull IList.View<Repo> view) {
        mUser = ((IRepos.View) view).getUser();
        super.onCreate(view);
    }

    @Override
    protected void realGetItems(boolean refresh, boolean more) {
        if (!more) {
            mService.listRepos(mUser).enqueue(getWeakListener(refresh, false));
        }
    }
}
