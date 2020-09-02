package com.louay.model.service.account;

import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.Users;

public interface AccountService {
    Boolean isExistAccount(Admin admin);

    Boolean isExistUsers(Users users);

    Admin createAccount(Admin admin);

    Users createUsers(Users users);

    Student createStudentsDetails(Student student);

    Instructor createInstructorsDetails(Instructor instructor);

    Admin deleteAccountByEmail(Admin admin);

    Users deleteUsersByUserID(Users user);

    Student deleteStudentsDetailsByStudentID(Student student);

    Instructor deleteInstructorsDetailsByInstructorID(Instructor instructor);

    Admin updateAccountByEmail(Admin account);

    Users updateUsersByUserID(Users user);

    Student updateStudentsDetailsByStudentID(Student student);

    Instructor updateInstructorsDetailsByInstructorID(Instructor instructor);

    Admin findAccountByEmail(Admin admin);

    Users findUsersByUserID(Users user);

    Student findStudentsDetailsByStudentID(Student student);

    Instructor findInstructorsDetailsByInstructorID(Instructor instructor);

    Student findStudentJoinCourseMemberByStudentId(Student student);
}
