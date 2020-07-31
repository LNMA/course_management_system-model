package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.courses.members.UsersAttendance;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.attendance.UsersAttendanceService;
import com.louay.model.service.course.CourseService;
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
public class UserAttendanceTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private UsersAttendanceService attendanceService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_UsersAttendanceService() {
        this.attendanceService = this.applicationContext.getBean(UsersAttendanceService.class);
    }

    @Test
    public void testCase01_create_account1() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("userAttendaceInstructor@test.com");
        admin.setPassword("1234");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createAccount(admin);
    }

    @Test
    public void testCase02_create_instructor() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("userAttendaceInstructor@test.com");
        instructor.setForename("user attendance");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        instructor.setBirthday(Date.valueOf("2020-07-26"));
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone(875454);
        instructor.setAddress("UserAttendanceTestCase");
        instructor.setHeadline("i am instructor");
        instructor.setNickname("dr");
        instructor.setSpecialty("math");
        instructor.setPortfolio("github");
        instructor.setProfileVisibility(InstructorProfileVisibility.PUBLIC);

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createInstructorsDetails(instructor);
    }

    @Test
    public void testCase03_create_account2() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("userAttendaceStudent@test.com");
        admin.setPassword("1234");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createAccount(admin);
    }

    @Test
    public void testCase04_create_student() {
        Student student = this.applicationContext.getBean("student",Student.class);
        student.setEmail("userAttendaceStudent@test.com");
        student.setForename("user attendance");
        student.setSurname("Test");
        student.setGender(Gender.MALE);
        student.setBirthday(Date.valueOf("2020-07-26"));
        student.setCountry("Test");
        student.setState("model");
        student.setPhone(875454);
        student.setAddress("UserAttendanceTestCase");
        student.setHeadline("i am instructor");
        student.setInterests("nothing");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createStudentsDetails(student);
    }

    @Test
    public void testCase05_create_course() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("userAttendaceInstructor@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseName("math");
        courses.setStartDate(java.sql.Date.valueOf("2020-07-26"));
        courses.setEndDate(java.sql.Date.valueOf("2020-07-27"));
        courses.setInstructor(instructor);

        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courseService.createCourse(courses);
    }

    @Test
    public void testCase06_create_userAttendance() {
        Student student = this.applicationContext.getBean(Student.class);
        student.setEmail("userAttendaceStudent@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        student = accountService.findStudentsDetailsByStudentID(student);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        UsersAttendance usersAttendance = this.applicationContext.getBean(UsersAttendance.class);
        usersAttendance.setStudent(student);
        usersAttendance.setCourse(courses);

        this.attendanceService.createUsersAttendance(usersAttendance);
    }

    @Test
    public void testCase07_find_and_update_userAttendance() {
        UsersAttendance usersAttendance = this.applicationContext.getBean(UsersAttendance.class);
        usersAttendance.setAttendancesId((long)1);

        usersAttendance = this.attendanceService.findUsersAttendanceByAttendanceId(usersAttendance);
        System.out.println(usersAttendance);

        this.attendanceService.updateUsersAttendance(usersAttendance);
    }

    @Test
    public void testCase08_find_and_delete_userAttendance() {
        UsersAttendance usersAttendance = this.applicationContext.getBean(UsersAttendance.class);
        usersAttendance.setAttendancesId((long)1);

        usersAttendance = this.attendanceService.findUsersAttendanceByAttendanceId(usersAttendance);
        System.out.println(usersAttendance);

        this.attendanceService.deleteUsersAttendanceByAttendanceId(usersAttendance);
    }

    @Test
    public void testCase09_find_and_delete_account1() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("userAttendaceInstructor@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);
        System.out.println(admin);
        accountService.deleteAccountByEmail(admin);
    }

    @Test
    public void testCase10_find_and_delete_account2() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("userAttendaceStudent@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);
        System.out.println(admin);
        accountService.deleteAccountByEmail(admin);
    }

}
