package org.cheshun.pushservice;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class AppSetup implements Setup {
	private static final Log log = Logs.getLog(AppSetup.class);

	@Override
	public void init(NutConfig nc) {
		log.debug("========== init ==========");
	}

	@Override
	public void destroy(NutConfig nc) {
		log.debug("========== destroy ==========");
	}

}
