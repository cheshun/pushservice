package org.cheshun.pushservice.filter;

import org.cheshun.pushservice.model.User;
import org.cheshun.pushservice.service.UserService;
import org.cheshun.pushservice.util.StringUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: CheShun
 * Date: 13-11-20
 * Time: 上午1:45
 * To change this template use File | Settings | File Templates.
 */
public class AuthFilter implements ActionFilter {
    private static final Log log = Logs.getLog(AuthFilter.class);

    private PropertiesProxy config;
    private Set<String> paths = new HashSet<String>();
    private Dao dao;

    public AuthFilter(PropertiesProxy config, Dao dao) {
        this.config = config;
        this.dao = dao;
        init();
    }

    private void init() {
        String pass = config.get("url.pass");
        if (StringUtil.noNull(pass)) {
            String[] passes = pass.split(",");
            for (String str: passes) {
                paths.add(str);
            }
        }
    }

    @Override
    public View match(ActionContext actionContext) {
        HttpServletRequest request = actionContext.getRequest();
        String path = actionContext.getPath();
        log.info("path-->" + path);
        if (paths.contains(path))
            return null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("push_user");
        if (user != null)
            return null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String email;
            String password;
            String[] infos = {};
            for (Cookie cookie: cookies) {
                String name = cookie.getName();
                if (name.equals("push_user")) {
                    String val = cookie.getValue();
                    infos = val.split("=");
                    break;
                }
            }
            if (infos.length == 2) {
                email = infos[0];
                password = infos[1];
                user = dao.fetch(User.class,
                        Cnd.where("email", "=", email).and("password", "=", password));
                if (user != null) {
                    request.getSession().setAttribute("push_user", user);
                    return null;
                }
            }
        }
        return new JspView("view/login");
    }
}
