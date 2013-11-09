package org.cheshun.pushservice;

import org.cheshun.pushservice.model.User;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class AppSetup implements Setup {
	private static final Log log = Logs.getLog(AppSetup.class);

	@Override
	public void init(NutConfig nc) {
		log.info("========== init ==========");
		Dao dao = nc.getIoc().get(NutDao.class, "dao");
		dao.create(User.class, false);
		User user = dao.fetch(User.class, "cheshun520@gmail.com");
		if (user == null) {
			user = new User();
			user.setEmail("cheshun520@gmail.com");
			user.setPassword("123456");
			user.setUsername("Admin");
			dao.insert(user); 
		}
	}

	@Override
	public void destroy(NutConfig nc) {
		log.info("========== destroy ==========");
	}

}
