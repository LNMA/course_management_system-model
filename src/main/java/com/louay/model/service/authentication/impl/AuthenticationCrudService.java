package com.louay.model.service.authentication.impl;

import com.louay.model.dao.authentication.CookieLoginDao;
import com.louay.model.dao.authentication.UserAuthenticationDao;
import com.louay.model.entity.authentication.CookieLogin;
import com.louay.model.entity.authentication.UsersAuthentication;
import com.louay.model.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class AuthenticationCrudService implements AuthenticationService, Serializable {
    private static final long serialVersionUID = -1931217921340122159L;
    private final UserAuthenticationDao userAuthenticationDao;
    private final CookieLoginDao cookieLoginDao;

    @Autowired
    public AuthenticationCrudService(UserAuthenticationDao userAuthenticationDao, CookieLoginDao cookieLoginDao) {
        if (userAuthenticationDao == null || cookieLoginDao == null){
            throw new IllegalArgumentException("DAO cannot be null at AuthenticationCrudService.class");
        }
        this.userAuthenticationDao = userAuthenticationDao;
        this.cookieLoginDao = cookieLoginDao;
    }

    private UserAuthenticationDao getUserAuthenticationDao() {
        return userAuthenticationDao;
    }

    private CookieLoginDao getCookieLoginDao() {
        return cookieLoginDao;
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

    @Transactional
    @Override
    public CookieLogin createCookieLogin(CookieLogin cookieLogin) {
        return getCookieLoginDao().save(cookieLogin);
    }

    @Transactional
    @Override
    public CookieLogin deleteCookieLoginByEmail(CookieLogin cookieLogin) {
        return getCookieLoginDao().delete(cookieLogin);
    }

    @Transactional
    @Override
    public CookieLogin updateCookieLogin(CookieLogin cookieLogin) {
        return getCookieLoginDao().update(cookieLogin);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public CookieLogin findCookieLoginByEmail(CookieLogin cookieLogin) {
        return getCookieLoginDao().findOneById(cookieLogin);
    }

    @Transactional
    @Override
    public Boolean isExistUsersAuthentication(UsersAuthentication usersAuthentication) {
        return getUserAuthenticationDao().isExist(usersAuthentication);
    }

    @Transactional
    @Override
    public Boolean isExistCookieLogin(CookieLogin cookieLogin) {
        return getCookieLoginDao().isExist(cookieLogin);
    }
}
