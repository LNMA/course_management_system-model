package com.louay.model.service.authentication;

import com.louay.model.entity.authentication.UsersAuthentication;

public interface AuthenticationService {
    UsersAuthentication createUsersAuthentication(UsersAuthentication usersAuthentication);

    UsersAuthentication deleteUsersAuthenticationByUserId(UsersAuthentication usersAuthentication);

    UsersAuthentication updateUsersAuthentication(UsersAuthentication usersAuthentication);

    UsersAuthentication findUsersAuthenticationByUserId(UsersAuthentication usersAuthentication);
}
