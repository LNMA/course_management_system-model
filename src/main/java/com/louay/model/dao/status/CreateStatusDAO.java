package com.louay.model.dao.status;

import com.louay.model.chains.status.UserStatus;
import com.louay.model.chains.status.CourseJoinStatus;
import com.louay.model.chains.status.SignInStatus;

public interface CreateStatusDAO {

    Long createUsersSignInDate(SignInStatus signInStatus);

    int createUsersStatus(UserStatus userStatus);

    int createUsersCourseJoin(CourseJoinStatus courseJoinStatus);
}
