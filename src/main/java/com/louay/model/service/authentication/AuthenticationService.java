package com.louay.model.service.authentication;

import com.louay.model.entity.authentication.CookieLogin;
import com.louay.model.entity.authentication.UsersAuthentication;

public interface AuthenticationService {
    UsersAuthentication createUsersAuthentication(UsersAuthentication usersAuthentication);

    UsersAuthentication deleteUsersAuthenticationByUserId(UsersAuthentication usersAuthentication);

    UsersAuthentication updateUsersAuthentication(UsersAuthentication usersAuthentication);

    UsersAuthentication findUsersAuthenticationByUserId(UsersAuthentication usersAuthentication);

    CookieLogin createCookieLogin(CookieLogin cookieLogin);

    CookieLogin deleteCookieLoginByEmail(CookieLogin cookieLogin);

    CookieLogin updateCookieLogin(CookieLogin cookieLogin);

    CookieLogin findCookieLoginByEmail(CookieLogin cookieLogin);

    Boolean isExistUsersAuthentication(UsersAuthentication usersAuthentication);

    Boolean isExistCookieLogin(CookieLogin cookieLogin);
}
