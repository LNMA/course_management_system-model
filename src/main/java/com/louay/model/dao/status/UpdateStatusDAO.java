package com.louay.model.dao.status;

import com.louay.model.chains.status.AccountStatus;
import com.louay.model.chains.status.CourseJoinStatus;
import com.louay.model.chains.status.SignInStatus;

public interface UpdateStatusDAO {

    int updateUsersSignInDate(SignInStatus signInStatus);

    int updateUsersStatus(AccountStatus accountStatus);

    int updateUsersCourseJoin(CourseJoinStatus courseJoinStatus);
}
