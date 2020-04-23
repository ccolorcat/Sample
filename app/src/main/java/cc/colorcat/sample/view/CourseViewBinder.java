package cc.colorcat.sample.view;

import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import cc.colorcat.adapter.AdapterViewHolder;
import cc.colorcat.adapter.ViewBinder2;
import cc.colorcat.sample.R;
import cc.colorcat.sample.entity.Course;
import cc.colorcat.vangogh.CircleTransformation;
import cc.colorcat.vangogh.Transformation;
import cc.colorcat.vangogh.VanGogh;

/**
 * Author: cxx
 * Date: 2020-04-23
 * GitHub: https://github.com/ccolorcat
 */
public class CourseViewBinder implements ViewBinder2<Course> {
    private Transformation transformation;

    public CourseViewBinder(float borderWidth, @ColorInt int borderColor) {
        transformation = new CircleTransformation(borderWidth, borderColor);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_course;
    }

    @Override
    public void bindView(@NonNull AdapterViewHolder holder, Course data) {
        VanGogh.get()
                .load(data.getPicSmall())
                .addTransformation(transformation)
                .into((ImageView) holder.get(R.id.iv_icon));
        holder.setText(R.id.tv_serial_number, Integer.toString(holder.getPosition()))
                .setText(R.id.tv_name, data.getName())
                .setText(R.id.tv_description, data.getDescription());
    }
}
