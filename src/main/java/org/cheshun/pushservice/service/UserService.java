package org.cheshun.pushservice.service;

import org.cheshun.pushservice.model.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

/**
 * Created with IntelliJ IDEA.
 * User: CheShun
 * Date: 13-11-27
 * Time: 下午11:57
 * To change this template use File | Settings | File Templates.
 */
public class UserService {
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public boolean checkLogin(String email, String password) {
        User user = dao.fetch(User.class, Cnd.where("email", "=", email).and("password", "=", password));
        return user == null ?false:true;

    }
}
