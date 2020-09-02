package com.louay.model.test_case;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAttendanceTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    @Autowired
    private UsersAttendanceService attendanceService;

    @Before
    public void initialize01_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Test
    public void testCase01_create_instructor() {
        Admin admin = new Admin();
        admin.setEmail("userAttendaceInstructor@test.com");
        admin.setPassword("1234");

        Instructor instructor = new Instructor();
        instructor.setAdmin(admin);
        instructor.setForename("user attendance");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 22);
        instructor.setBirthday(calendar);
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone("875454");
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
    public void testCase02_create_student() {
        Admin admin = new Admin();
        admin.setEmail("userAttendaceStudent@test.com");
        admin.setPassword("1234");

        Student student = new Student();
        student.setAdmin(admin);
        student.setForename("user attendance");
        student.setSurname("Test");
        student.setGender(Gender.MALE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 22);
        student.setBirthday(calendar);
        student.setCountry("Test");
        student.setState("model");
        student.setPhone("875454");
        student.setAddress("UserAttendanceTestCase");
        student.setHeadline("i am instructor");
        student.setInterests("nothing");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createStudentsDetails(student);
    }

    @Test
    public void testCase03_create_course() {
        Instructor instructor = new Instructor();
        instructor.setEmail("userAttendaceInstructor@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        Courses courses = new Courses();
        courses.setCourseName("math");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JULY,26);
        courses.setStartDate(calendar);
        calendar.set(2020,Calendar.JULY,27);
        courses.setEndDate(calendar);
        courses.setInstructor(instructor);

        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courseService.createCourse(courses);
    }

    @Test
    public void testCase04_create_userAttendance() {
        Student student = new Student();
        student.setEmail("userAttendaceStudent@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        student = accountService.findStudentsDetailsByStudentID(student);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        UsersAttendance usersAttendance = new UsersAttendance();
        usersAttendance.setStudent(student);
        usersAttendance.setCourse(courses);

        this.attendanceService.createUsersAttendance(usersAttendance);
    }

    @Test
    public void testCase05_find_and_update_userAttendance() {
        UsersAttendance usersAttendance = new UsersAttendance();
        usersAttendance.setAttendancesId((long)1);

        usersAttendance = this.attendanceService.findUsersAttendanceByAttendanceId(usersAttendance);
        System.out.println(usersAttendance);

        this.attendanceService.updateUsersAttendance(usersAttendance);
    }

    @Test
    public void testCase06_find_and_delete_userAttendance() {
        UsersAttendance usersAttendance = new UsersAttendance();
        usersAttendance.setAttendancesId((long)1);

        usersAttendance = this.attendanceService.findUsersAttendanceByAttendanceId(usersAttendance);
        System.out.println(usersAttendance);

        this.attendanceService.deleteUsersAttendanceByAttendanceId(usersAttendance);
    }

    @Test
    public void testCase07_find_and_delete_account1() {
        Instructor instructor = new Instructor();
        instructor.setEmail("userAttendaceInstructor@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        System.out.println(instructor);

        accountService.deleteInstructorsDetailsByInstructorID(instructor);
    }

    @Test
    public void testCase08_find_and_delete_account2() {
        Student student = new Student();
        student.setEmail("userAttendaceStudent@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        student = accountService.findStudentsDetailsByStudentID(student);

        System.out.println(student);

        accountService.deleteStudentsDetailsByStudentID(student);
    }

}
