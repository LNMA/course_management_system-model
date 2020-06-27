package com.louay.model.dao.accounts;

import com.louay.model.chains.users.*;

public interface DeleteAccountsDAO {

    int deleteUsersByEmail(Accounts account);

    int deleteUsersDetailsByUserID(Users user);

    int deleteStudentsDetailsByStudentID(Student student);

    int deleteInstructorsDetailsByInstructorID(Instructor instructor);

    int deleteUsersRolesByUserIDAndRoleID(AccountsRoles accountsRoles);

    int deleteRoleByRoleID(AccountsRoles accountsRoles);

    int deleteUsersProfilePictureByUserID(Accounts account);
}
