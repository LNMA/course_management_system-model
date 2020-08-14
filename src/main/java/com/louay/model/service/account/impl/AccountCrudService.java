package com.louay.model.service.account.impl;

import com.louay.model.dao.account.AccountDao;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.Users;
import com.louay.model.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class AccountCrudService implements AccountService, Serializable {
    private static final long serialVersionUID = -3626190829855605565L;
    private final AccountDao accountDao;

    @Autowired
    public AccountCrudService(AccountDao accountDao) {
        if (accountDao == null){
            throw new IllegalArgumentException("DAO cannot be null at AccountCrudService.class");
        }
        this.accountDao = accountDao;
    }

    private AccountDao getAccountDao() {
        return this.accountDao;
    }

    @Transactional
    @Override
    public Admin createAccount(Admin admin) {
        return getAccountDao().save(admin);
    }

    @Transactional
    @Override
    public Users createUsers(Users users) {
        return getAccountDao().save(users);
    }

    @Transactional
    @Override
    public Student createStudentsDetails(Student student) {
        return getAccountDao().save(student);
    }

    @Transactional
    @Override
    public Instructor createInstructorsDetails(Instructor instructor) {
        return getAccountDao().save(instructor);
    }

    @Transactional
    @Override
    public Admin deleteAccountByEmail(Admin admin) {
        return getAccountDao().delete(admin);
    }

    @Transactional
    @Override
    public Users deleteUsersByUserID(Users user) {
        return getAccountDao().delete(user);
    }

    @Transactional
    @Override
    public Student deleteStudentsDetailsByStudentID(Student student) {
        return getAccountDao().delete(student);
    }

    @Transactional
    @Override
    public Instructor deleteInstructorsDetailsByInstructorID(Instructor instructor) {
        return getAccountDao().delete(instructor);
    }

    @Transactional
    @Override
    public Admin updateAccountByEmail(Admin account) {
        return getAccountDao().update(account);
    }

    @Transactional
    @Override
    public Users updateUsersByUserID(Users user) {
        return getAccountDao().update(user);
    }

    @Transactional
    @Override
    public Student updateStudentsDetailsByStudentID(Student student) {
        return getAccountDao().update(student);
    }

    @Transactional
    @Override
    public Instructor updateInstructorsDetailsByInstructorID(Instructor instructor) {
        return getAccountDao().update(instructor);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Admin findAccountByEmail(Admin admin) {
        return getAccountDao().findOneById(admin);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Users findUsersByUserID(Users user) {
        return getAccountDao().findOneById(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Student findStudentsDetailsByStudentID(Student student) {
        return getAccountDao().findOneById(student);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Instructor findInstructorsDetailsByInstructorID(Instructor instructor) {
        return getAccountDao().findOneById(instructor);
    }
}
