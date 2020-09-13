package com.louay.model.service.member.impl;

import com.louay.model.dao.member.CourseMemberDao;
import com.louay.model.entity.courses.members.CourseMembers;
import com.louay.model.service.member.CourseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Set;

@Service
public class CourseMemberCrudService implements CourseMemberService, Serializable {
    private static final long serialVersionUID = -1291056893222662927L;
    private final CourseMemberDao courseMemberDao;

    @Autowired
    public CourseMemberCrudService(CourseMemberDao courseMemberDao) {
        if (courseMemberDao == null){
            throw new IllegalArgumentException("DAO cannot be null at CourseMemberCrudService.class");
        }
        this.courseMemberDao = courseMemberDao;
    }

    private CourseMemberDao getCourseMemberDao() {
        return courseMemberDao;
    }

    @Transactional
    @Override
    public Boolean isStudentMemberAtAnyCourse(CourseMembers courseMembers) {
        return getCourseMemberDao().isStudentMemberAtAnyCourse(courseMembers);
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

    @Transactional
    @Override
    public Boolean isStudentMemberAtThisCourse(CourseMembers courseMembers) {
        return getCourseMemberDao().isStudentMemberAtThisCourse(courseMembers);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Set<CourseMembers> findCourseMemberByCourseId(CourseMembers courseMembers) {
        return getCourseMemberDao().findCourseMemberByCourseId(courseMembers);
    }
}
