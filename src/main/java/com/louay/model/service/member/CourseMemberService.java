package com.louay.model.service.member;

import com.louay.model.entity.courses.members.CourseMembers;

import java.util.Set;

public interface CourseMemberService {
    CourseMembers createCourseMember(CourseMembers courseMembers);

    CourseMembers deleteCourseMemberByMemberId(CourseMembers courseMembers);

    CourseMembers updateMember(CourseMembers courseMembers);

    CourseMembers findMemberByMemberId(CourseMembers courseMembers);

    Boolean isStudentMemberAtAnyCourse(CourseMembers courseMembers);

    Boolean isStudentMemberAtThisCourse(CourseMembers courseMembers);

    Set<CourseMembers> findCourseMemberByCourseId(CourseMembers courseMembers);
}
