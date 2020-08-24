package com.louay.model.service.status.impl;

import com.louay.model.dao.status.UserAccountStatusDao;
import com.louay.model.dao.status.UserAtCourseDao;
import com.louay.model.dao.status.UserSignInDao;
import com.louay.model.entity.status.UserAccountStatus;
import com.louay.model.entity.status.UserAtCourse;
import com.louay.model.entity.status.UserSignIn;
import com.louay.model.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class StatusCrudService implements StatusService, Serializable {
    private static final long serialVersionUID = -8963067237499038425L;
    private final UserAtCourseDao userAtCourseDao;
    private final UserAccountStatusDao userAccountStatusDao;
    private final UserSignInDao userSignInDao;

    @Autowired
    public StatusCrudService(UserAtCourseDao userAtCourseDao, UserAccountStatusDao userAccountStatusDao, UserSignInDao userSignInDao) {
        if (userAccountStatusDao == null || userAtCourseDao == null || userSignInDao == null){
            throw new IllegalArgumentException("DAO cannot be null at StatusCrudService.class");
        }
        this.userAtCourseDao = userAtCourseDao;
        this.userAccountStatusDao = userAccountStatusDao;
        this.userSignInDao = userSignInDao;
    }

    private UserAtCourseDao getUserAtCourseDao() {
        return userAtCourseDao;
    }

    private UserAccountStatusDao getUserAccountStatusDao() {
        return userAccountStatusDao;
    }

    private UserSignInDao getUserSignInDao() {
        return userSignInDao;
    }

    @Transactional
    @Override
    public UserAtCourse createUserAtCourse(UserAtCourse userAtCourse) {
        return getUserAtCourseDao().save(userAtCourse);
    }

    @Transactional
    @Override
    public UserAtCourse deleteUserAtCourseByUserId(UserAtCourse userAtCourse) {
        return getUserAtCourseDao().delete(userAtCourse);
    }

    @Transactional
    @Override
    public UserAtCourse updateUserAtCourse(UserAtCourse userAtCourse) {
        return getUserAtCourseDao().update(userAtCourse);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public UserAtCourse findUserAtCourseByUserId(UserAtCourse userAtCourse) {
        return getUserAtCourseDao().findOneById(userAtCourse);
    }

    @Transactional
    @Override
    public UserAccountStatus createUserAccountStatus(UserAccountStatus userAccountStatus) {
        return getUserAccountStatusDao().save(userAccountStatus);
    }

    @Transactional
    @Override
    public UserAccountStatus deleteUserAccountStatusByUserId(UserAccountStatus userAccountStatus) {
        return getUserAccountStatusDao().delete(userAccountStatus);
    }

    @Transactional
    @Override
    public UserAccountStatus updateUserAccountStatus(UserAccountStatus userAccountStatus) {
        return getUserAccountStatusDao().update(userAccountStatus);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public UserAccountStatus findUserAccountStatusByUserId(UserAccountStatus userAccountStatus) {
        return getUserAccountStatusDao().findOneById(userAccountStatus);
    }

    @Transactional
    @Override
    public UserSignIn createUserSignIn(UserSignIn userSignIn) {
        return getUserSignInDao().save(userSignIn);
    }

    @Transactional
    @Override
    public UserSignIn deleteUserSignInBySignInId(UserSignIn userSignIn) {
        return getUserSignInDao().delete(userSignIn);
    }

    @Transactional
    @Override
    public UserSignIn updateUserSignIn(UserSignIn userSignIn) {
        return getUserSignInDao().update(userSignIn);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public UserSignIn findUserSignInBySignInId(UserSignIn userSignIn) {
        return getUserSignInDao().findOneById(userSignIn);
    }
}
