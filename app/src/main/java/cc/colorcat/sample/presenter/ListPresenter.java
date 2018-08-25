package cc.colorcat.sample.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import cc.colorcat.sample.api.WeakListener;
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

    protected WeakListener<IList.View<T>, List<T>> getWeakListener(final boolean refresh, final boolean more) {
        return new WeakListener<IList.View<T>, List<T>>(mView) {

            @Override
            public void onStart(IList.View<T> view) {
                super.onStart(view);
                view.setRefreshing(true);
            }

            @Override
            public void onSuccess(IList.View<T> view, List<T> data) {
                if (more) {
                    view.addMoreItems(data);
                } else {
                    view.refreshItems(data);
                }
            }

            @Override
            public void onFinish(IList.View<T> view) {
                super.onFinish(view);
                view.setRefreshing(false);
            }
        };
    }
}
