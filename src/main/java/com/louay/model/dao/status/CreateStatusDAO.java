package com.louay.model.dao.status;

import com.louay.model.chains.status.AccountStatus;
import com.louay.model.chains.status.CourseJoinStatus;
import com.louay.model.chains.status.SignInStatus;

public interface CreateStatusDAO {

    int createUsersSignInDate(SignInStatus signInStatus);

    int createUsersStatus(AccountStatus accountStatus);

    int createUsersCourseJoin(CourseJoinStatus courseJoinStatus);
}
