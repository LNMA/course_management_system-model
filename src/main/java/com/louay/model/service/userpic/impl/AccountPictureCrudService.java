package com.louay.model.service.userpic.impl;

import com.louay.model.dao.userpic.AccountPictureDao;
import com.louay.model.entity.users.picute.AccountPicture;
import com.louay.model.service.userpic.AccountPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class AccountPictureCrudService implements AccountPictureService, Serializable {
    private static final long serialVersionUID = 546049780487186833L;
    private final AccountPictureDao accountPictureDao;

    @Autowired
    public AccountPictureCrudService(AccountPictureDao accountPictureDao) {
        if (accountPictureDao == null){
            throw new IllegalArgumentException("DAO cannot be null at AccountPictureCrudService.class");
        }
        this.accountPictureDao = accountPictureDao;
    }

    private AccountPictureDao getAccountPictureDao() {
        return accountPictureDao;
    }

    @Transactional
    @Override
    public AccountPicture createAccountPicture(AccountPicture accountPicture) {
        return getAccountPictureDao().save(accountPicture);
    }

    @Transactional
    @Override
    public AccountPicture deleteAccountPictureByUserId(AccountPicture accountPicture) {
        return getAccountPictureDao().delete(accountPicture);
    }

    @Transactional
    @Override
    public AccountPicture updateAccountPicture(AccountPicture accountPicture) {
        return getAccountPictureDao().update(accountPicture);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public AccountPicture findAccountPictureByUserId(AccountPicture accountPicture) {
        return getAccountPictureDao().findOneById(accountPicture);
    }
}
