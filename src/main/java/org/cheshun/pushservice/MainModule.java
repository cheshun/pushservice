package org.cheshun.pushservice;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cheshun.pushservice.filter.AuthFilter;
import org.cheshun.pushservice.model.User;
import org.cheshun.pushservice.util.MD5Util;
import org.cheshun.pushservice.util.StringUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.JsonIocProvider;

@Modules(scanPackage = true)
@SetupBy(AppSetup.class)
@IocBy(type=JsonIocProvider.class, args={"ioc"})
@Filters(@By(type = AuthFilter.class, args = {"ioc:authFilter"}))
public class MainModule {
	private static final Log log = Logs.getLog(MainModule.class);

	@At("/index")
	@Ok("jsp:view.index")
	public void index() {
		log.debug("被访问");
	}

	@At("/login")
	@Ok("jsp:view.login")
	@GET
	public void login() {
		log.debug("登陆");
	}

	@At("/login")
	@POST
	public void login(HttpServletRequest request,
			HttpServletResponse response, Ioc ioc) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remstr = request.getParameter("remember");
        int remember = 0;
        if (StringUtil.noNull(remstr))
            remember = Integer.parseInt(remstr);
		Dao dao = ioc.get(NutDao.class, "dao");
        log.debug("password--->"+ password);
        password = MD5Util.generalMD5E2(password);
		User user = dao.fetch(User.class, Cnd.where("email", "=", email).and("password", "=", password));
		if (user != null) {
            request.getSession().setAttribute("push_user", user);

            if (remember == 1){
                Cookie cookie = new Cookie("push_user",email +"="+ password);
                cookie.setMaxAge(86400);
                cookie.setPath("/push");
                response.addCookie(cookie);
            }
			response.sendRedirect("index");
		} else {
			response.sendRedirect("login");
		}
	}
}
