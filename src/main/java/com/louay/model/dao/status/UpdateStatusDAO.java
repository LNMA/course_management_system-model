package com.louay.model.dao.status;

import com.louay.model.chains.status.UserStatus;
import com.louay.model.chains.status.CourseJoinStatus;
import com.louay.model.chains.status.SignInStatus;

public interface UpdateStatusDAO {

    int updateUsersSignInDateByUserSignInID(SignInStatus signInStatus);

    int updateUsersStatusByUserID(UserStatus userStatus);

    int updateUsersCourseJoinByUserID(CourseJoinStatus courseJoinStatus);
}
