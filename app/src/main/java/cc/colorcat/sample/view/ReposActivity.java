package cc.colorcat.sample.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import cc.colorcat.adapter.RvAdapter;
import cc.colorcat.adapter.RvHolder;
import cc.colorcat.adapter.SimpleRvAdapter;
import cc.colorcat.sample.R;
import cc.colorcat.sample.entity.License;
import cc.colorcat.sample.entity.Owner;
import cc.colorcat.sample.entity.Repo;
import cc.colorcat.sample.presenter.ReposPresenter;
import cc.colorcat.vangogh.VanGogh;

/**
 * Author: cxx
 * Date: 2018-08-26
 * GitHub: https://github.com/ccolorcat
 */
public class ReposActivity extends ListActivity<Repo, ReposPresenter> {
    public static final String USER = "user";

    public static void start(Context context, String user) {
        Intent intent = new Intent(context, ReposActivity.class);
        intent.putExtra(USER, user);
        context.startActivity(intent);
    }

    @Override
    public String getExtra(String key) {
        if (USER.equals(key)) {
            String user = getIntent().getStringExtra(USER);
            if (TextUtils.isEmpty(user)) {
                throw new IllegalStateException("has no user");
            }
            return user;
        }
        return "";
    }

    @Override
    protected boolean refreshEnabled() {
        return false;
    }

    @Override
    protected ReposPresenter getPresenter() {
        return new ReposPresenter();
    }

    @Override
    protected RvAdapter getRvAdapter(List<Repo> items) {
        return new SimpleRvAdapter<Repo>(items, R.layout.item_repo) {
            @Override
            public void bindView(@NonNull RvHolder holder, @NonNull Repo data) {
                String licenseName = "";
                License license = data.getLicense();
                if (license != null) {
                    licenseName = license.getName();
                }
                String avatar = "";
                Owner owner = data.getOwner();
                if (owner != null) {
                    avatar = owner.getAvatarUrl();
                }
                String description = data.getDescription();
                RvHolder.Helper helper = holder.getHelper();
                helper.setText(R.id.tv_name, data.getName())
                        .setText(R.id.tv_description, description)
                        .setVisibility(R.id.tv_description, TextUtils.isEmpty(description) ? View.GONE : View.VISIBLE)
                        .setText(R.id.tv_license, licenseName)
                        .setVisibility(R.id.tv_license, TextUtils.isEmpty(licenseName) ? View.GONE : View.VISIBLE)
                        .setText(R.id.tv_html_link, Html.fromHtml(data.getHtmlUrl()));
                VanGogh.get().load(avatar).into((ImageView) helper.get(R.id.iv_icon));
            }
        };
    }
}
