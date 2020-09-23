package com.louay.model.dao.attendance.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.attendance.UsersAttendanceDao;
import com.louay.model.entity.courses.members.UsersAttendance;
import com.louay.model.entity.wrapper.StudentAttendanceReport;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.*;

@Repository
public class UsersAttendanceRepository extends CommonDaoImpl<UsersAttendance> implements UsersAttendanceDao {
    private static final long serialVersionUID = -3087997609004929270L;

    @Override
    public <S extends UsersAttendance> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT u From UsersAttendance u WHERE u.attendancesId = :attendancesId")
                .setParameter("attendancesId", entity.getAttendancesId())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends UsersAttendance> S delete(S entity) {
        Class<? extends UsersAttendance> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getAttendancesId());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends UsersAttendance> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends UsersAttendance> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getAttendancesId());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends UsersAttendance> S findOneById(S entity) {
        Class<? extends UsersAttendance> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getAttendancesId());
        return result;
    }

    @Override
    public <S extends UsersAttendance> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UsersAttendance> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getAttendancesId());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public List<UsersAttendance> findUsersAttendanceByCourseAndDate(
            StudentAttendanceReport studentAttendanceReport) {
        return getEntityManager().createQuery("SELECT ua FROM " +
                "UsersAttendance ua JOIN FETCH ua.student WHERE ua.attendanceDate >= :fromDate " +
                "AND ua.attendanceDate <= :toDate AND ua.course.courseID = :courseId", UsersAttendance.class)
                .setParameter("courseId", studentAttendanceReport.getUsersAttendance().getCourse().getCourseID())
                .setParameter("fromDate", studentAttendanceReport.getFromDate(), TemporalType.TIMESTAMP)
                .setParameter("toDate", studentAttendanceReport.getToDate(), TemporalType.TIMESTAMP)
                .getResultList();
    }

    @Override
    public List<UsersAttendance> findUsersAttendanceByCourse(UsersAttendance usersAttendance) {
        return getEntityManager().createQuery("SELECT ua FROM " +
                "UsersAttendance ua JOIN FETCH ua.student WHERE ua.course.courseID = :courseId", UsersAttendance.class)
                .setParameter("courseId", usersAttendance.getCourse().getCourseID())
                .getResultList();
    }
}
