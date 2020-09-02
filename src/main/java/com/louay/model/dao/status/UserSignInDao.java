package com.louay.model.dao.status;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.status.UserSignIn;

import java.util.List;

public interface UserSignInDao extends CommonDao<UserSignIn>, GenericDao<UserSignIn> {
    Boolean isUserSignIn(UserSignIn userSignIn);

    List<UserSignIn> findUserSignInByUserId(UserSignIn userSignIn);
}
