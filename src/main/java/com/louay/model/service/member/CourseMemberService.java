package com.louay.model.service.member;

import com.louay.model.entity.courses.members.CourseMembers;

public interface CourseMemberService {
    CourseMembers createCourseMember(CourseMembers courseMembers);

    CourseMembers deleteCourseMemberByMemberId(CourseMembers courseMembers);

    CourseMembers updateMember(CourseMembers courseMembers);

    CourseMembers findMemberByMemberId(CourseMembers courseMembers);
}
