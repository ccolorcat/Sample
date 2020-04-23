package cc.colorcat.sample.presenter;

import androidx.annotation.NonNull;

import java.util.List;

import cc.colorcat.sample.api.ApiListener;
import cc.colorcat.sample.api.WeakListener;
import cc.colorcat.sample.contact.IList;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public abstract class ListPresenter<T> extends BasePresenter<IList.View<T>> implements IList.Presenter<T> {
    protected boolean mHasMore = true;

    @Override
    public void onCreate(@NonNull IList.View<T> view) {
        super.onCreate(view);
        doGetItems();
    }

    @Override
    public void doGetItems() {
        realGetItems(false, false, getWeakListener(false));
    }

    @Override
    public void toRefreshItems() {
        realGetItems(true, false, getWeakListener(false));
    }

    @Override
    public void toGetMoreItems() {
        if (mHasMore) {
            realGetItems(false, true, getWeakListener(true));
        }
    }

    protected abstract void realGetItems(boolean refresh, boolean more, ApiListener<List<T>> listener);

    protected WeakListener<IList.View<T>, List<T>> getWeakListener(final boolean more) {
        return new WeakListener<IList.View<T>, List<T>>(mView) {

            @Override
            public void onStart(IList.View<T> view) {
                super.onStart(view);
                view.setRefreshing(true);
            }

            @Override
            public void onSuccess(IList.View<T> view, List<T> data) {
                mHasMore = !data.isEmpty();
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
