package com.louay.model.dao.account.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.account.AccountDao;
import com.louay.model.entity.users.Accounts;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.Users;
import com.louay.model.entity.wrapper.GeneralSearch;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccountRepository extends CommonDaoImpl<Accounts> implements AccountDao {
    private static final long serialVersionUID = 1041973583206522237L;

    @Override
    public <S extends Accounts> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT a From Admin a WHERE a.email = :email")
                .setParameter("email", entity.getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends Accounts> S delete(S entity) {
        Class<? extends Accounts> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends Accounts> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends Accounts> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getEmail());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends Accounts> S findOneById(S entity) {
        Class<? extends Accounts> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getEmail());
        return result;
    }

    @Override
    public <S extends Accounts> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends Accounts> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getEmail());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public Student findStudentJoinCourseMemberByStudentId(Student student) {
        return (Student) getEntityManager().createQuery("SELECT s From Student s INNER JOIN FETCH " +
                "s.courseMembers cm WHERE s.email = :email")
                .setParameter("email", student.getEmail())
                .getSingleResult();
    }

    @Override
    public Set<Users> findUserLikePagination(GeneralSearch generalSearch) {
        String key = generalSearch.getKey()+"%";
        int firstResultValue = (generalSearch.getPageNumber() - 1) * generalSearch.getPageSize();
        List<Users> usersList =  getEntityManager().createQuery("SELECT u FROM Users u WHERE " +
                "u.email LIKE :email OR u.forename LIKE :forename OR u.surname LIKE :surname", Users.class)
                .setParameter("email", key)
                .setParameter("forename", key)
                .setParameter("surname", key)
                .setFirstResult(firstResultValue)
                .setMaxResults(generalSearch.getPageSize())
                .getResultList();

        return new HashSet<>(usersList);
    }

    @Override
    public Long getCountUserLikePagination(GeneralSearch generalSearch) {
        String key = generalSearch.getKey()+"%";
        return getEntityManager().createQuery("SELECT COUNT(u) FROM Users u WHERE " +
                "u.email LIKE :email OR u.forename LIKE :forename OR u.surname LIKE :surname", Long.class)
                .setParameter("email", key)
                .setParameter("forename", key)
                .setParameter("surname", key)
                .setMaxResults(1)
                .getResultList()
                .get(0);
    }
}
