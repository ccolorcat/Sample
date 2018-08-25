package cc.colorcat.sample.api;

import java.util.List;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.sample.entity.Course;
import cc.colorcat.sample.entity.Repo;

/**
 * Author: cxx
 * Date: 2018-08-23
 * GitHub: https://github.com/ccolorcat
 */
public interface Api {
    ApiSender<List<Course>> listCourses(int type, int number);

    ApiSender<List<Repo>> listRepos(String user);
}
