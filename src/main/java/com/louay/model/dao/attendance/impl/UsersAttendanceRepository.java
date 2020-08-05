package com.louay.model.dao.attendance.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.attendance.UsersAttendanceDao;
import com.louay.model.entity.courses.members.UsersAttendance;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.*;

@Repository
public class UsersAttendanceRepository extends CommonDaoImpl<UsersAttendance> implements UsersAttendanceDao {
    private static final long serialVersionUID = 3366538993387667661L;

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
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends UsersAttendance> S findOneById(S entity) {
        Class<? extends UsersAttendance> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getAttendancesId(), LockModeType.PESSIMISTIC_READ);
        return result;
    }

    @Override
    public <S extends UsersAttendance> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UsersAttendance> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getAttendancesId(), LockModeType.PESSIMISTIC_READ);
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
