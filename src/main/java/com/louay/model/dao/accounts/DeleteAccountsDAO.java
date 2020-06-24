package com.louay.model.dao.accounts;

import com.louay.model.chains.users.Accounts;
import com.louay.model.chains.users.Instructor;
import com.louay.model.chains.users.Student;
import com.louay.model.chains.users.Users;

public interface DeleteAccountsDAO {

    int deleteUsers(Accounts account);

    int deleteUsersDetails(Users user);

    int deleteStudentsDetails(Student student);

    int deleteInstructorsDetails(Instructor instructor);

    int deleteUsersRoles(Accounts account);

    int deleteRole(Accounts account);

    int deleteUsersProfilePicture(Accounts account);
}
