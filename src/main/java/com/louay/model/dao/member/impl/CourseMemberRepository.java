package com.louay.model.dao.member.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.member.CourseMemberDao;
import com.louay.model.entity.courses.members.CourseMembers;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.*;

@Repository
public class CourseMemberRepository extends CommonDaoImpl<CourseMembers> implements CourseMemberDao {
    private static final long serialVersionUID = 1988966839713599110L;

    @Override
    public <S extends CourseMembers> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From CourseMembers c WHERE c.memberId = :memberId")
                .setParameter("memberId", entity.getMemberId())
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
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends CourseMembers> S findOneById(S entity) {
        Class<? extends CourseMembers> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getMemberId(), LockModeType.PESSIMISTIC_READ);
        return result;
    }

    @Override
    public <S extends CourseMembers> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends CourseMembers> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getMemberId(), LockModeType.PESSIMISTIC_READ);
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
