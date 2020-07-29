package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.account.AccountService;
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
public class CourseTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private CourseService courseService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_CourseService() {
        this.courseService = this.applicationContext.getBean(CourseService.class);
    }

    @Test
    public void testCase01_create_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("course@test.com");
        admin.setPassword("1234");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createAccount(admin);
    }

    @Test
    public void testCase02_create_instructor() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("course@test.com");
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

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createInstructorsDetails(instructor);
    }

    @Test
    public void testCase03_create_course() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("course@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseName("math");
        courses.setStartDate(java.sql.Date.valueOf("2020-07-26"));
        courses.setEndDate(java.sql.Date.valueOf("2020-07-27"));
        courses.setInstructor(instructor);

        this.courseService.createCourse(courses);
    }

    @Test
    public void testCase04_find_and_update_course() {
        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long)1);

        courses = this.courseService.findCourseByCourseId(courses);

        System.out.println(courses);
        courses.setCourseName("science");

        this.courseService.updateCourse(courses);
    }

    @Test
    public void testCase05_find_and_delete_course() {
        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long)1);

        courses = this.courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        this.courseService.deleteCourseByCourseId(courses);
    }

    @Test
    public void testCase06_find_and_delete_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("course@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);
        System.out.println(admin);
        accountService.deleteAccountByEmail(admin);
    }
}


