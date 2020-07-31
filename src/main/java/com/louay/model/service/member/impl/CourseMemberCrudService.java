package com.louay.model.service.member.impl;

import com.louay.model.dao.member.CourseMemberDao;
import com.louay.model.entity.courses.members.CourseMembers;
import com.louay.model.service.member.CourseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class CourseMemberCrudService implements CourseMemberService, Serializable {
    private static final long serialVersionUID = -5824570950859492038L;
    private CourseMemberDao courseMemberDao;

    public CourseMemberDao getCourseMemberDao() {
        return courseMemberDao;
    }

    @Autowired
    public void setCourseMemberDao(CourseMemberDao courseMemberDao) {
        this.courseMemberDao = courseMemberDao;
    }

    @Transactional
    @Override
    public CourseMembers createCourseMember(CourseMembers courseMembers) {
        return getCourseMemberDao().save(courseMembers);
    }

    @Transactional
    @Override
    public CourseMembers deleteCourseMemberByMemberId(CourseMembers courseMembers) {
        return getCourseMemberDao().delete(courseMembers);
    }

    @Transactional
    @Override
    public CourseMembers updateMember(CourseMembers courseMembers) {
        return getCourseMemberDao().update(courseMembers);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public CourseMembers findMemberByMemberId(CourseMembers courseMembers) {
        return getCourseMemberDao().findOneById(courseMembers);
    }
}
