package com.louay.model.service.role.impl;

import com.louay.model.dao.role.AccountRolesDao;
import com.louay.model.dao.role.UsersRolesDao;
import com.louay.model.entity.role.AccountsRoles;
import com.louay.model.entity.role.UsersRoles;
import com.louay.model.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class RoleCrudService implements RoleService, Serializable {
    private static final long serialVersionUID = -569575096437487460L;
    private final AccountRolesDao accountRolesDao;
    private final UsersRolesDao usersRolesDao;

    @Autowired
    public RoleCrudService(AccountRolesDao accountRolesDao, UsersRolesDao usersRolesDao) {
        if (accountRolesDao == null || usersRolesDao == null){
            throw new IllegalArgumentException("DAO cannot be null at RoleCrudService.class");
        }
        this.accountRolesDao = accountRolesDao;
        this.usersRolesDao = usersRolesDao;
    }

    private AccountRolesDao getAccountRolesDao() {
        return accountRolesDao;
    }

    private UsersRolesDao getUsersRolesDao() {
        return usersRolesDao;
    }

    @Transactional
    @Override
    public AccountsRoles createAccountRole(AccountsRoles accountsRoles) {
        return getAccountRolesDao().save(accountsRoles);
    }

    @Transactional
    @Override
    public UsersRoles createUsersRoles(UsersRoles usersRoles) {
        return getUsersRolesDao().save(usersRoles);
    }

    @Transactional
    @Override
    public AccountsRoles deleteAccountRoleByRoleId(AccountsRoles accountsRoles) {
        return getAccountRolesDao().delete(accountsRoles);
    }

    @Transactional
    @Override
    public UsersRoles deleteUsersRolesByUserId(UsersRoles usersRoles) {
        return getUsersRolesDao().delete(usersRoles);
    }

    @Transactional
    @Override
    public AccountsRoles updateAccountRole(AccountsRoles accountsRoles) {
        return getAccountRolesDao().update(accountsRoles);
    }

    @Transactional
    @Override
    public UsersRoles updateUsersRoles(UsersRoles usersRoles) {
        return getUsersRolesDao().update(usersRoles);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public AccountsRoles findAccountRoleByRoleId(AccountsRoles accountsRoles) {
        return getAccountRolesDao().findOneById(accountsRoles);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public UsersRoles findUsersRolesByUserId(UsersRoles usersRoles) {
        return getUsersRolesDao().findOneById(usersRoles);
    }
}
