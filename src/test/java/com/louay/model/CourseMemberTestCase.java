package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.courses.members.CourseMembers;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.course.CourseService;
import com.louay.model.service.member.CourseMemberService;
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
public class CourseMemberTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private CourseMemberService courseMemberService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_CourseMemberService() {
        this.courseMemberService = this.applicationContext.getBean(CourseMemberService.class);
    }

    @Test
    public void testCase01_create_account1() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("courseMemberInstructor@test.com");
        admin.setPassword("1234");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createAccount(admin);
    }

    @Test
    public void testCase02_create_instructor() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("courseMemberInstructor@test.com");
        instructor.setForename("Course Member");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        instructor.setBirthday(Date.valueOf("2020-07-26"));
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone(875454);
        instructor.setAddress("CourseMemberTestCase");
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
        admin.setEmail("courseMemberStudent@test.com");
        admin.setPassword("1234");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createAccount(admin);
    }

    @Test
    public void testCase04_create_student() {
        Student student = this.applicationContext.getBean("student",Student.class);
        student.setEmail("courseMemberStudent@test.com");
        student.setForename("Course Member");
        student.setSurname("Test");
        student.setGender(Gender.MALE);
        student.setBirthday(Date.valueOf("2020-07-26"));
        student.setCountry("Test");
        student.setState("model");
        student.setPhone(875454);
        student.setAddress("CourseMemberTestCase");
        student.setHeadline("i am instructor");
        student.setInterests("nothing");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createStudentsDetails(student);
    }

    @Test
    public void testCase05_create_course() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("courseMemberInstructor@test.com");
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
    public void testCase06_create_courseMember() {
        Student student = this.applicationContext.getBean(Student.class);
        student.setEmail("courseMemberStudent@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        student = accountService.findStudentsDetailsByStudentID(student);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        CourseMembers courseMembers = this.applicationContext.getBean(CourseMembers.class);
        courseMembers.setStudent(student);
        courseMembers.setCourse(courses);

        this.courseMemberService.createCourseMember(courseMembers);
    }

    @Test
    public void testCase07_find_courseMember() {
        CourseMembers courseMembers = this.applicationContext.getBean(CourseMembers.class);
        courseMembers.setMemberId((long)1);

        courseMembers = this.courseMemberService.findMemberByMemberId(courseMembers);
        System.out.println(courseMembers);
    }

    @Test
    public void testCase08_find_and_delete_courseMember() {
        CourseMembers courseMembers = this.applicationContext.getBean(CourseMembers.class);
        courseMembers.setMemberId((long) 1);

        courseMembers = this.courseMemberService.findMemberByMemberId(courseMembers);
        System.out.println(courseMembers);

        this.courseMemberService.deleteCourseMemberByMemberId(courseMembers);
    }

    @Test
    public void testCase09_find_and_delete_account1() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("courseMemberInstructor@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);
        System.out.println(admin);
        accountService.deleteAccountByEmail(admin);
    }

    @Test
    public void testCase10_find_and_delete_account2() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("courseMemberStudent@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);
        System.out.println(admin);
        accountService.deleteAccountByEmail(admin);
    }

}


