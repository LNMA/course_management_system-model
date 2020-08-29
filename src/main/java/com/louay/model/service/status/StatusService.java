package com.louay.model.service.status;

import com.louay.model.entity.status.UserAccountStatus;
import com.louay.model.entity.status.UserAtCourse;
import com.louay.model.entity.status.UserSignIn;

import java.util.List;

public interface StatusService {
    UserAtCourse createUserAtCourse(UserAtCourse userAtCourse);

    UserAtCourse deleteUserAtCourseByUserId(UserAtCourse userAtCourse);

    UserAtCourse updateUserAtCourse(UserAtCourse userAtCourse);

    UserAtCourse findUserAtCourseByUserId(UserAtCourse userAtCourse);

    UserAccountStatus createUserAccountStatus(UserAccountStatus userAccountStatus);

    UserAccountStatus deleteUserAccountStatusByUserId(UserAccountStatus userAccountStatus);

    UserAccountStatus updateUserAccountStatus(UserAccountStatus userAccountStatus);

    UserAccountStatus findUserAccountStatusByUserId(UserAccountStatus userAccountStatus);

    UserSignIn createUserSignIn(UserSignIn userSignIn);

    UserSignIn deleteUserSignInBySignInId(UserSignIn userSignIn);

    UserSignIn updateUserSignIn(UserSignIn userSignIn);

    UserSignIn findUserSignInBySignInId(UserSignIn userSignIn);

    List<UserSignIn> findUserSignInByUserId(UserSignIn userSignIn);

    Boolean isUserSignInExist(UserSignIn userSignIn);

    Boolean isUserAtCourseExist(UserAtCourse userAtCourse);
}
