package com.louay.model.service.role;

import com.louay.model.entity.role.AccountsRoles;
import com.louay.model.entity.role.UsersRoles;

public interface RoleService {
    AccountsRoles createAccountRole(AccountsRoles accountsRoles);

    UsersRoles createUsersRoles(UsersRoles usersRoles);

    AccountsRoles deleteAccountRoleByRoleId(AccountsRoles accountsRoles);

    UsersRoles deleteUsersRolesByUserId(UsersRoles usersRoles);

    AccountsRoles updateAccountRole(AccountsRoles accountsRoles);

    UsersRoles updateUsersRoles(UsersRoles usersRoles);

    AccountsRoles findAccountRoleByRoleId(AccountsRoles accountsRoles);

    UsersRoles findUsersRolesByUserId(UsersRoles usersRoles);
}
