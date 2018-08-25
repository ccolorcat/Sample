package cc.colorcat.sample.api;

import java.util.List;

import cc.colorcat.netbird.MRequest;
import cc.colorcat.parser.gson.GsonParser;
import cc.colorcat.sample.entity.Course;
import cc.colorcat.sample.entity.Repo;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public final class ApiService implements Api {
    @Override
    public ApiSender<List<Course>> listCourses(int type, int number) {
        return new BaseService<List<Course>>() {}
                .path("api/teacher")
                .add("type", type)
                .add("num", number)
                .get();
    }

    @Override
    public ApiSender<List<Repo>> listRepos(String user) {
        return new BaseService<List<Repo>>() {
            @Override
            protected MRequest.Builder<List<Repo>> createBuilder() {
                return new MRequest.Builder<>(new GsonParser<List<Repo>>() {});
            }
        }.url("https://api.github.com/").path("users/" + user + "/repos").get();
    }
}
