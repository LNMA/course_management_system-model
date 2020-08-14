package com.louay.model.test_case;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.status.UserAccountStatus;
import com.louay.model.entity.status.UserAtCourse;
import com.louay.model.entity.status.UserSignIn;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Users;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.status.StatusService;
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
public class StatusTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private StatusService statusService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_StatusService() {
        this.statusService = this.applicationContext.getBean(StatusService.class);
    }

    @Test
    public void testCase01_create_users() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("status@test.com");
        admin.setPassword("1234");

        Users users = this.applicationContext.getBean("users", Users.class);
        users.setAdmin(admin);
        users.setForename("Users Status");
        users.setSurname("Test");
        users.setGender(Gender.MALE);
        users.setBirthday(Date.valueOf("2020-07-26"));
        users.setCountry("Test");
        users.setState("modal");
        users.setPhone("123456");
        users.setAddress("StatusTestCaseClass");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createUsers(users);
    }

    @Test
    public void testCase02_create_userAccountStatus() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        UserAccountStatus userAccountStatus = this.applicationContext.getBean(UserAccountStatus.class);
        userAccountStatus.setUsers(users);
        userAccountStatus.setValid(false);
        userAccountStatus.setOnline(false);

        this.statusService.createUserAccountStatus(userAccountStatus);

        System.out.println(userAccountStatus);
    }

    @Test
    public void testCase03_find_and_update_userAccountStatus() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");

        UserAccountStatus userAccountStatus = this.applicationContext.getBean(UserAccountStatus.class);
        userAccountStatus.setUsers(users);

        userAccountStatus = this.statusService.findUserAccountStatusByUserId(userAccountStatus);
        userAccountStatus.setValid(true);
        userAccountStatus.setOnline(true);

        this.statusService.updateUserAccountStatus(userAccountStatus);

        System.out.println(userAccountStatus);
    }

    @Test
    public void testCase04_delete_userAccountStatus() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");

        UserAccountStatus userAccountStatus = this.applicationContext.getBean(UserAccountStatus.class);
        userAccountStatus.setUsers(users);

        this.statusService.deleteUserAccountStatusByUserId(userAccountStatus);
    }

    @Test
    public void testCase05_create_UserAtCourse() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        UserAtCourse userAtCourse = this.applicationContext.getBean(UserAtCourse.class);
        userAtCourse.setUsers(users);
        userAtCourse.setBusy(true);

        this.statusService.createUserAtCourse(userAtCourse);

        System.out.println(userAtCourse);
    }

    @Test
    public void testCase06_find_and_update_UserAtCourse() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");

        UserAtCourse userAtCourse = this.applicationContext.getBean(UserAtCourse.class);
        userAtCourse.setUsers(users);
        userAtCourse = this.statusService.findUserAtCourseByUserId(userAtCourse);
        userAtCourse.setBusy(false);

        this.statusService.updateUserAtCourse(userAtCourse);

        System.out.println(userAtCourse);
    }

    @Test
    public void testCase07_delete_UserAtCourse() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");

        UserAtCourse userAtCourse = this.applicationContext.getBean(UserAtCourse.class);
        userAtCourse.setUsers(users);

        this.statusService.deleteUserAtCourseByUserId(userAtCourse);
    }

    @Test
    public void testCase08_create_UserSignIn() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        UserSignIn userSignIn = this.applicationContext.getBean(UserSignIn.class);
        userSignIn.setUsers(users);

        this.statusService.createUserSignIn(userSignIn);

        System.out.println(userSignIn);
    }

    @Test
    public void testCase09_find_and_update_UserSignIn() {
        UserSignIn userSignIn = this.applicationContext.getBean(UserSignIn.class);
        userSignIn.setUserSignInId((long) 1);

        userSignIn = this.statusService.findUserSignInBySignInId(userSignIn);

        this.statusService.updateUserSignIn(userSignIn);

        System.out.println(userSignIn);
    }

    @Test
    public void testCase10_delete_UserSignIn() {
        UserSignIn userSignIn = this.applicationContext.getBean(UserSignIn.class);
        userSignIn.setUserSignInId((long) 1);

        this.statusService.deleteUserSignInBySignInId(userSignIn);
    }

    @Test
    public void testCase11_delete_User() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("status@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.deleteUsersByUserID(users);
    }
}
