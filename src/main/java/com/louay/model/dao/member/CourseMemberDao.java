package com.louay.model.dao.member;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.courses.members.CourseMembers;

import java.util.Set;

public interface CourseMemberDao extends GenericDao<CourseMembers>, CommonDao<CourseMembers> {
    Boolean isStudentMemberAtAnyCourse(CourseMembers courseMembers);

    Boolean isStudentMemberAtThisCourse(CourseMembers courseMembers);

    Set<CourseMembers> findCourseMemberEagerStudentByCourseId(CourseMembers courseMembers);

    Set<CourseMembers> findLazyCourseMemberByCourseId(CourseMembers courseMembers);
}
