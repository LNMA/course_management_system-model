package com.louay.model.dao.accounts;

import com.louay.model.chains.users.*;

public interface UpdateAccountsDAO {

    int updateUsersByEmail(Accounts account);

    int updateUsersDetailsByUserID(Users user);

    int updateStudentsDetailsByStudentID(Student student);

    int updateInstructorsDetailsByInstructorID(Instructor instructor);

    int updateRoleByRoleID(AccountsRoles accountsRoles);

    int updateUsersProfilePictureByUserID(Accounts account);
}
