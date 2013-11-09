package org.cheshun.pushservice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.cheshun.pushservice.model.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.JsonIocProvider;

@Modules(scanPackage = true)
@SetupBy(AppSetup.class)
@IocBy(type=JsonIocProvider.class, args={"ioc"})
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
	public void login(String email, String password, int remember,
			HttpServletResponse response, Ioc ioc) throws IOException {
		Dao dao = ioc.get(NutDao.class, "dao");
		User user = dao.fetch(User.class, Cnd.where("email", "=", email).and("password", "=", password));
		if (user != null) {
			response.sendRedirect("index");
		} else {
			response.sendRedirect("login");
		}
	}
}
