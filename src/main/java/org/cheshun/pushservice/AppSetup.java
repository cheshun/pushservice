package org.cheshun.pushservice;

import org.cheshun.pushservice.model.Clazz;
import org.cheshun.pushservice.model.College;
import org.cheshun.pushservice.model.Major;
import org.cheshun.pushservice.model.Student;
import org.cheshun.pushservice.model.University;
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
		dao.create(User.class, true);
		dao.create(University.class, true);
		dao.create(College.class, true);
		dao.create(Major.class, true);
		dao.create(Clazz.class, true);
		dao.create(Student.class, true);
		User user = dao.fetch(User.class, "cheshun520@gmail.com");
		if (user == null) {
			user = new User();
			user.setEmail("cheshun520@gmail.com");
			user.setPassword("123456");
			user.setUsername("Admin");
			dao.insert(user); 
		}
		University university = new University();
		university.setUniversityName("大连民族学院");
		university = dao.insert(university);
		College college = new College();
		college.setCollegeName("计算机科学与工程学院");
		college.setUniversityId(university.getUniversityId());
		college.setUniversity(university);
		college = dao.insert(college);
		Major major = new Major();
		major.setMajorName("计算机科学与技术");
		major.setCollegeId(college.getCollegeId());
		major.setCollege(college);
		major = dao.insert(major);
		Clazz clazz = new Clazz();
		clazz.setClazzName("计科105班");
		clazz.setMajorId(major.getMajorId());
		clazz.setMajor(major);
		clazz = dao.insert(clazz);
		Student student = new Student();
		student.setStudentId("2010081501");
		student.setStudentName("车顺");
		student.setClazzId(clazz.getClazzId());
		student.setClazz(clazz);
		dao.insert(student);
	}

	@Override
	public void destroy(NutConfig nc) {
		log.info("========== destroy ==========");
	}

}
