package com.louay.model.dao.accounts;

import com.louay.model.chains.users.Accounts;
import com.louay.model.chains.users.Instructor;
import com.louay.model.chains.users.Student;
import com.louay.model.chains.users.Users;

public interface CreateAccountsDAO {

    int createUsers(Accounts account);

    int createUsersDetails(Users user);

    int createStudentsDetails(Student student);

    int createInstructorsDetails(Instructor instructor);

    int createUsersRoles(Accounts account);

    int createRole(Accounts account);

    int createUsersProfilePicture(Accounts account);
}
