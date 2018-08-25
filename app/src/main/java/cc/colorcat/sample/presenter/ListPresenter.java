package cc.colorcat.sample.presenter;

import android.support.annotation.NonNull;

import cc.colorcat.sample.contact.IList;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public abstract class ListPresenter<T> extends BasePresenter<IList.View<T>> implements IList.Presenter<T> {
    @Override
    public void onCreate(@NonNull IList.View<T> view) {
        super.onCreate(view);
        doGetItems();
    }

    @Override
    public void doGetItems() {
        realGetItems(false, false);
    }

    @Override
    public void toRefreshItems() {
        realGetItems(true, false);
    }

    @Override
    public void toGetMoreItems() {
        realGetItems(false, true);
    }

    protected abstract void realGetItems(boolean refresh, boolean more);
}
