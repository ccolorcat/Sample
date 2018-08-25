package cc.colorcat.sample.contact;

import cc.colorcat.sample.entity.Repo;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public interface IRepos {
    interface View extends IList.View<Repo> {
        String getUser();
    }

    interface Presenter extends IList.Presenter<Repo> {

    }
}
