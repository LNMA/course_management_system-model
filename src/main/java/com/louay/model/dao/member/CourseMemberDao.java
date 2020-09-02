package com.louay.model.dao.member;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.courses.members.CourseMembers;

public interface CourseMemberDao extends GenericDao<CourseMembers>, CommonDao<CourseMembers> {
    Boolean isStudentMemberAtAnyCourse(CourseMembers courseMembers);
}
