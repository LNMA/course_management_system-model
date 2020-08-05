package com.louay.model.test_case;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.Users;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.account.AccountService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountTestCase extends TestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private AccountService accountService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_AccountService() {
        this.accountService = this.applicationContext.getBean(AccountService.class);
    }

    @Test
    public void testCase01_create_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("account@test.com");
        admin.setPassword("1234");

        this.accountService.createAccount(admin);

        System.out.println(admin);
    }

    @Test
    public void testCase02_find_and_update_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("account@test.com");
        admin = this.accountService.findAccountByEmail(admin);

        admin.setPassword("123");
        this.accountService.updateAccountByEmail(admin);

        System.out.println(admin);
    }

    @Test
    public void testCase03_find_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("account@test.com");

        admin = this.accountService.findAccountByEmail(admin);

        System.out.println(admin);
    }

    @Test
    public void testCase04_find_and_delete_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("account@test.com");

        System.out.println(admin);

        this.accountService.deleteAccountByEmail(admin);
    }

    @Test
    public void testCase05_create_users() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("accountUsers@test.com");
        admin.setPassword("1234");

        Users users = this.applicationContext.getBean("users",Users.class);
        users.setAdmin(admin);
        users.setForename("Account");
        users.setSurname("Test");
        users.setGender(Gender.MALE);
        users.setBirthday(Date.valueOf("2020-07-26"));
        users.setCountry("Test");
        users.setState("model");
        users.setPhone(875454);
        users.setAddress("AccountTestCaseClass");

        this.accountService.createUsers(users);

        System.out.println(users);
    }

    @Test
    public void testCase06_find_user() {
        Users users = this.applicationContext.getBean("users",Users.class);
        users.setEmail("accountUsers@test.com");

        users = this.accountService.findUsersByUserID(users);

        System.out.println(users);
    }

    @Test
    public void testCase07_find_and_update_users() {
        Users users = this.applicationContext.getBean("users",Users.class);
        users.setEmail("accountUsers@test.com");

        users = this.accountService.findUsersByUserID(users);
        users.setForename("Account");
        users.setSurname("Test");

        this.accountService.updateUsersByUserID(users);

        System.out.println(users);
    }

    @Test
    public void testCase08_find_and_delete_users() {
        Users users = this.applicationContext.getBean("users",Users.class);
        users.setEmail("accountUsers@test.com");

        this.accountService.deleteUsersByUserID(users);
    }

    @Test
    public void testCase09_create_instructor() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("accountUsersInstructor@test.com");
        admin.setPassword("12345");

        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setAdmin(admin);
        instructor.setForename("Account");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        instructor.setBirthday(Date.valueOf("2020-07-26"));
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone(875454);
        instructor.setAddress("AccountTestCaseClass");
        instructor.setHeadline("i am instructor");
        instructor.setNickname("dr");
        instructor.setSpecialty("math");
        instructor.setPortfolio("github");
        instructor.setProfileVisibility(InstructorProfileVisibility.PUBLIC);

        this.accountService.createInstructorsDetails(instructor);

        System.out.println(instructor);
    }

    @Test
    public void testCase10_find_and_update_instructor() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("accountUsersInstructor@test.com");
        instructor = this.accountService.findInstructorsDetailsByInstructorID(instructor);

        instructor.setSpecialty("science");
        instructor.setNickname("prof.");

        this.accountService.updateInstructorsDetailsByInstructorID(instructor);

        System.out.println(instructor);
    }

    @Test
    public void testCase11_find_instructor() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("accountUsersInstructor@test.com");

        instructor = this.accountService.findInstructorsDetailsByInstructorID(instructor);

        System.out.println(instructor);
    }

    @Test
    public void testCase12_find_and_delete_instructor() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("accountUsersInstructor@test.com");

        this.accountService.deleteInstructorsDetailsByInstructorID(instructor);
    }

    @Test
    public void testCase13_create_student() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("accountUserStudent@test.com");
        admin.setPassword("123456");

        Student student = this.applicationContext.getBean("student",Student.class);
        student.setAdmin(admin);
        student.setForename("Account");
        student.setSurname("Test");
        student.setGender(Gender.MALE);
        student.setBirthday(Date.valueOf("2020-07-26"));
        student.setCountry("Test");
        student.setState("model");
        student.setPhone(875454);
        student.setAddress("AccountTestCaseClass");
        student.setGender(Gender.MALE);
        student.setBirthday(Date.valueOf("2020-07-26"));
        student.setCountry("Test");
        student.setState("Model");
        student.setPhone(123456);
        student.setAddress("AccountTestCaseClass");
        student.setHeadline("i am student");
        student.setInterests("football");

        this.accountService.createStudentsDetails(student);

        System.out.println(student);
    }

    @Test
    public void testCase14_find_and_update_student() {
        Student student = this.applicationContext.getBean("student",Student.class);
        student.setEmail("accountUserStudent@test.com");
        student = this.accountService.findStudentsDetailsByStudentID(student);

        student.setHeadline("i am update student");
        student.setInterests("nothing");

        this.accountService.updateStudentsDetailsByStudentID(student);

        System.out.println(student);
    }

    @Test
    public void testCase15_find_student() {
        Student student = this.applicationContext.getBean("student",Student.class);
        student.setEmail("accountUserStudent@test.com");

        student = this.accountService.findStudentsDetailsByStudentID(student);

        System.out.println(student);
    }

    @Test
    public void testCase16_find_and_delete_student() {
        Student student = this.applicationContext.getBean("student",Student.class);
        student.setEmail("accountUserStudent@test.com");

        this.accountService.deleteStudentsDetailsByStudentID(student);
    }
}
