package cc.colorcat.sample.view;

import java.util.List;

import cc.colorcat.adapter.RvAdapter;
import cc.colorcat.sample.entity.Repo;
import cc.colorcat.sample.presenter.ReposPresenter;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public class ReposActivity extends ListActivity<Repo, ReposPresenter> {
    @Override
    protected ReposPresenter getPresenter() {
        return null;
    }

    @Override
    protected RvAdapter getRvAdapter(List<Repo> items) {
        return null;
    }
}
