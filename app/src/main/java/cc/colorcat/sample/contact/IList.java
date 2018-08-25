package cc.colorcat.sample.contact;

import java.util.List;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public interface IList {
    interface View<T> extends IBase.View{
        void refreshItems(List<T> items);

        void addMoreItems(List<T> items);

        void setRefreshing(boolean refreshing);
    }

    interface Presenter<T> extends IBase.Presenter<View<T>> {
        void doGetItems();

        void toRefreshItems();

        void toGetMoreItems();
    }
}
