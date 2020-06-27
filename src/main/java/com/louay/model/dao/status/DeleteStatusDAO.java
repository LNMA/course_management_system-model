package com.louay.model.dao.status;

import com.louay.model.chains.status.UserStatus;
import com.louay.model.chains.status.CourseJoinStatus;
import com.louay.model.chains.status.SignInStatus;

public interface DeleteStatusDAO {

    int deleteUsersSignInDateByUserSignInID(SignInStatus signInStatus);

    int deleteUsersStatusByUserID(UserStatus userStatus);

    int deleteUsersCourseJoinByUserID(CourseJoinStatus courseJoinStatus);
}
