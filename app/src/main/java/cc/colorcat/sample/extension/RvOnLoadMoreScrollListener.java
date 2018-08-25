package cc.colorcat.sample.extension;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public abstract class RvOnLoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private boolean mIgnoreScrollDirection;
    private boolean mUpOnLast = false; // 如果手指是向上滑（即滚动条向下滚）则为 true，否则为 false

    public RvOnLoadMoreScrollListener() {
        this(false);
    }

    public RvOnLoadMoreScrollListener(boolean ignoreScrollDirection) {
        mIgnoreScrollDirection = ignoreScrollDirection;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        int total = manager.getItemCount();
        View view = manager.findViewByPosition(total - 1);
        // 如果手指是向上滑（即滚动条向下滚），且最后一个 item 所对应的 View 在屏幕内（即可见），且当前没有滚动就加载更多。
        if (mUpOnLast && view != null && newState == RecyclerView.SCROLL_STATE_IDLE) {
            onLoadMore();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mUpOnLast = mIgnoreScrollDirection || dy > 0; // 记录滚动方向，如果 dy > 0 则表示是 ScrollDown (即手指往上滑，滚动条向下)
    }

    public abstract void onLoadMore();
}

