package com.louay.model.dao.member.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.member.CourseMemberDao;
import com.louay.model.entity.courses.members.CourseMembers;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseMemberRepository extends CommonDaoImpl<CourseMembers> implements CourseMemberDao {
    private static final long serialVersionUID = -512106875905605310L;

    @Override
    public <S extends CourseMembers> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From CourseMembers c WHERE c.memberId = :memberId")
                .setParameter("memberId", entity.getMemberId())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public Boolean isStudentMemberAtAnyCourse(CourseMembers courseMembers) {
        return !getEntityManager().createQuery("SELECT c From CourseMembers c WHERE c.student.email = :email")
                .setParameter("email", courseMembers.getStudent().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public Boolean isStudentMemberAtThisCourse(CourseMembers courseMembers) {
        return !getEntityManager().createQuery("SELECT c From CourseMembers c WHERE " +
                "c.course.courseID = :courseId AND c.student.email = :email")
                .setParameter("courseId", courseMembers.getCourse().getCourseID())
                .setParameter("email", courseMembers.getStudent().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends CourseMembers> S delete(S entity) {
        Class<? extends CourseMembers> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getMemberId());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends CourseMembers> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends CourseMembers> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getMemberId());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends CourseMembers> S findOneById(S entity) {
        Class<? extends CourseMembers> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getMemberId());
        return result;
    }

    @Override
    public <S extends CourseMembers> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends CourseMembers> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getMemberId());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public Set<CourseMembers> findCourseMemberEagerStudentByCourseId(CourseMembers courseMembers) {
        List<CourseMembers> courseMembersList = getEntityManager().createQuery("SELECT cm FROM " +
                "CourseMembers cm JOIN FETCH cm.student WHERE cm.course.courseID = " +
                ":courseId", CourseMembers.class)
                .setParameter("courseId", courseMembers.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(courseMembersList);
    }

    @Override
    public Set<CourseMembers> findLazyCourseMemberByCourseId(CourseMembers courseMembers) {
        List<CourseMembers> courseMembersList = getEntityManager().createQuery("SELECT cm FROM " +
                "CourseMembers cm WHERE cm.course.courseID = :courseId", CourseMembers.class)
                .setParameter("courseId", courseMembers.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(courseMembersList);
    }
}
