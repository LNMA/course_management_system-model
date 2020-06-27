package com.louay.model.dao.accounts;

import com.louay.model.chains.users.*;

public interface CreateAccountsDAO {

    int createUsers(Accounts account);

    int createUsersDetails(Users user);

    int createStudentsDetails(Student student);

    int createInstructorsDetails(Instructor instructor);

    int createUsersRoles(AccountsRoles accountsRoles);

    Long createRole(AccountsRoles accountsRoles);

    int createUsersProfilePicture(Accounts account);
}
