package com.louay.model.dao.status;

import com.louay.model.chains.status.AccountStatus;
import com.louay.model.chains.status.CourseJoinStatus;
import com.louay.model.chains.status.SignInStatus;

public interface DeleteStatusDAO {

    int deleteUsersSignInDate(SignInStatus signInStatus);

    int deleteUsersStatus(AccountStatus accountStatus);

    int deleteUsersCourseJoin(CourseJoinStatus courseJoinStatus);
}
