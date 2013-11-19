package org.cheshun.pushservice.action;

import org.cheshun.pushservice.model.University;
import org.cheshun.pushservice.util.StringUtil;
import org.nutz.dao.Dao;
import org.nutz.mvc.annotation.At;

/**
 * Created with IntelliJ IDEA.
 * User: CheShun
 * Date: 13-11-20
 * Time: 上午1:00
 * To change this template use File | Settings | File Templates.
 */
@At("/university")
public class UniversityAction {
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }
    @At("/add")
    public University add(String universityName) {
        if (StringUtil.noNull(universityName))
            return null;
        University university = new University();
        university.setUniversityName(universityName);
        return dao.insert(university);
    }

}
