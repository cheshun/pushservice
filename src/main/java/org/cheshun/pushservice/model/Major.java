package org.cheshun.pushservice.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("push_major")
public class Major {
	@Id
	private Integer majorId;
	@Column
	private String majorName;
    @Column
    private Integer universityId;
    @One(target = University.class, field = "universityId")
    private University university;
	@Column
	private Integer collegeId;
	@One(target=College.class, field="collegeId")
	private College college;
	@Many(target=Clazz.class, field="majorId")
	private List<Clazz> clazzs;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public List<Clazz> getClazzs() {
        return clazzs;
    }

    public void setClazzs(List<Clazz> clazzs) {
        this.clazzs = clazzs;
    }
}
