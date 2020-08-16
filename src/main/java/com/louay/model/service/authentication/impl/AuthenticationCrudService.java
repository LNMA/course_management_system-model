package com.louay.model.service.authentication.impl;

import com.louay.model.dao.authentication.UserAuthenticationDao;
import com.louay.model.entity.authentication.UsersAuthentication;
import com.louay.model.service.authentication.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class AuthenticationCrudService implements AuthenticationService, Serializable {
    private static final long serialVersionUID = 4048832549817897728L;
    private final UserAuthenticationDao userAuthenticationDao;

    public AuthenticationCrudService(UserAuthenticationDao userAuthenticationDao) {
        this.userAuthenticationDao = userAuthenticationDao;
    }

    private UserAuthenticationDao getUserAuthenticationDao() {
        return userAuthenticationDao;
    }

    @Transactional
    @Override
    public UsersAuthentication createUsersAuthentication(UsersAuthentication usersAuthentication) {
        return getUserAuthenticationDao().save(usersAuthentication);
    }

    @Transactional
    @Override
    public UsersAuthentication deleteUsersAuthenticationByUserId(UsersAuthentication usersAuthentication) {
        return getUserAuthenticationDao().delete(usersAuthentication);
    }

    @Transactional
    @Override
    public UsersAuthentication updateUsersAuthentication(UsersAuthentication usersAuthentication) {
        return getUserAuthenticationDao().update(usersAuthentication);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public UsersAuthentication findUsersAuthenticationByUserId(UsersAuthentication usersAuthentication) {
        return getUserAuthenticationDao().findOneById(usersAuthentication);
    }
}
