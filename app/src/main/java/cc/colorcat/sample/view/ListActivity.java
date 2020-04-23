package cc.colorcat.sample.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cc.colorcat.adapter.RvAdapter;
import cc.colorcat.sample.R;
import cc.colorcat.sample.contact.IList;
import cc.colorcat.sample.extension.RvOnLoadMoreScrollListener;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public abstract class ListActivity<T, P extends IList.Presenter<T>> extends BaseActivity implements IList.View<T> {
    private P mPresenter;
    private SwipeRefreshLayout mRefreshLayout;
    private List<T> mItems = new ArrayList<>();
    private RvAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mPresenter = getPresenter();

        RecyclerView rv = findViewById(R.id.rv_items);
        rv.setLayoutManager(getLayoutManager());
        mAdapter = getRvAdapter(mItems);
        rv.setAdapter(mAdapter);
        RecyclerView.ItemDecoration decoration = getItemDecoration();
        if (decoration != null) {
            rv.addItemDecoration(decoration);
        }
        if (loadMoreEnabled()) {
            rv.addOnScrollListener(new RvOnLoadMoreScrollListener() {
                @Override
                public void onLoadMore() {
                    mPresenter.toGetMoreItems();
                }
            });
        }

        mRefreshLayout = findViewById(R.id.srl_root);
        if (refreshEnabled()) {
            mRefreshLayout.setEnabled(true);
            mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.toRefreshItems();
                }
            });
        } else {
            mRefreshLayout.setEnabled(false);
        }

        mPresenter.onCreate(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void refreshItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMoreItems(List<T> items) {
        int size = mItems.size();
        mItems.addAll(items);
        mAdapter.notifyItemRangeInserted(size, items.size());
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public String getExtra(String key) {
        throw new UnsupportedOperationException();
    }

    protected boolean loadMoreEnabled() {
        return false;
    }

    protected boolean refreshEnabled() {
        return true;
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    }

    protected abstract RvAdapter getRvAdapter(List<T> items);

    protected abstract P getPresenter();
}
