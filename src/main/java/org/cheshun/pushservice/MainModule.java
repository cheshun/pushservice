package org.cheshun.pushservice;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;

@Modules(scanPackage = true)
@SetupBy(AppSetup.class)
public class MainModule {
	private static final Log log = Logs.getLog(MainModule.class);
	
	@At("/index")
	@Ok("jsp:view.index")
	public void index() {
		log.debug("±ª∑√Œ ");
	}
}
