package com.louay.model.dao.accounts;

import com.louay.model.chains.users.Accounts;
import com.louay.model.chains.users.Instructor;
import com.louay.model.chains.users.Student;
import com.louay.model.chains.users.Users;

public interface UpdateAccountsDAO {

    int updateUsers(Accounts account);

    int updateUsersDetails(Users user);

    int updateStudentsDetails(Student student);

    int updateInstructorsDetails(Instructor instructor);

    int updateUsersRoles(Accounts account);

    int updateRole(Accounts account);

    int updateUsersProfilePicture(Accounts account);
}
