package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.feedback.CourseFeedback;
import com.louay.model.entity.feedback.FileFeedback;
import com.louay.model.entity.feedback.FileMessageFeedback;
import com.louay.model.entity.feedback.MessageFeedback;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Users;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.course.CourseService;
import com.louay.model.service.feedback.FeedbackService;
import com.louay.model.util.file.FileProcess;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedbackTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private FeedbackService feedbackService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_feedbackService() {
        this.feedbackService = this.applicationContext.getBean(FeedbackService.class);
    }

    @Test
    public void testCase01_create_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("feedback@test.com");
        admin.setPassword("1234");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createAccount(admin);
    }

    @Test
    public void testCase02_create_instructor() {
        Instructor instructor = this.applicationContext.getBean("instructor", Instructor.class);
        instructor.setEmail("feedback@test.com");
        instructor.setForename("Feedback");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        instructor.setBirthday(Date.valueOf("2020-07-26"));
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone(875454);
        instructor.setAddress("FeedbackTestCase");
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
        Instructor instructor = this.applicationContext.getBean("instructor", Instructor.class);
        instructor.setEmail("feedback@test.com");
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
    public void testCase04_create_feedbackMessage() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("feedback@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        System.out.println(users);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        CourseFeedback courseFeedback = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback.setCourse(courses);
        courseFeedback.setUser(users);

        System.out.println(courseFeedback);

        MessageFeedback messageFeedback = this.applicationContext.getBean(MessageFeedback.class);
        messageFeedback.setCourseFeedback(courseFeedback);
        messageFeedback.setPostMessage("This feedback is for test only.");

        this.feedbackService.createMessageFeedback(messageFeedback);
        System.out.println(messageFeedback);
    }

    @Test
    public void testCase05_update_feedbackMessage(){
        CourseFeedback courseFeedback = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback.setFeedbackID((long) 1);
        courseFeedback = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        System.out.println(courseFeedback);

        MessageFeedback messageFeedback = this.applicationContext.getBean(MessageFeedback.class);
        messageFeedback.setCourseFeedback(courseFeedback);
        messageFeedback = this.feedbackService.findMessageFeedbackByFeedbackId(messageFeedback);
        messageFeedback.setPostMessage("new update feedback");

        System.out.println(messageFeedback);

        this.feedbackService.updateMessageFeedback(messageFeedback);

        System.out.println(messageFeedback);

    }

    @Test
    public void testCase06_create_feedbackFile(){
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("feedback@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        System.out.println(users);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        CourseFeedback courseFeedback = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback.setCourse(courses);
        courseFeedback.setUser(users);

        System.out.println(courseFeedback);

        FileFeedback fileFeedback = this.applicationContext.getBean(FileFeedback.class);

        FileProcess fileProcess = this.applicationContext.getBean(FileProcess.class);
        byte [] bytes = null;
        try {
            //TODO : change picture path
            bytes = fileProcess.readFile("C:\\Users\\Oday Amr\\Documents\\IdeaProjects\\" +
                    "course_management_system-model\\Louay Amr.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileFeedback.setFile(bytes);
        fileFeedback.setFileExtension("Louay Amr.png");
        fileFeedback.setCourseFeedback(courseFeedback);

        this.feedbackService.createFileFeedback(fileFeedback);

        System.out.println(fileFeedback);
    }

    @Test
    public void testCase07_update_feedbackFile(){
        CourseFeedback courseFeedback = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback.setFeedbackID((long) 2);
        courseFeedback = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        System.out.println(courseFeedback);

        FileFeedback fileFeedback = this.applicationContext.getBean(FileFeedback.class);
        fileFeedback.setCourseFeedback(courseFeedback);
        fileFeedback = this.feedbackService.findFileFeedbackByFeedbackId(fileFeedback);

        FileProcess fileProcess = this.applicationContext.getBean(FileProcess.class);
        byte [] bytes = null;
        try {
            //TODO : change picture path
            bytes = fileProcess.readFile("C:\\Users\\Oday Amr\\Documents\\IdeaProjects\\" +
                    "course_management_system-model\\Louay Amr_black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileFeedback.setFile(bytes);
        fileFeedback.setFileExtension("Louay Amr_black.png");

        this.feedbackService.updateFileFeedback(fileFeedback);

        System.out.println(fileFeedback);
    }

    @Test
    public void testCase08_create_feedbackFileMessage(){
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("feedback@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        CourseFeedback courseFeedback = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback.setCourse(courses);
        courseFeedback.setUser(users);

        System.out.println(courseFeedback);

        FileMessageFeedback fileMessageFeedback = this.applicationContext.getBean(FileMessageFeedback.class);

        FileProcess fileProcess = this.applicationContext.getBean(FileProcess.class);
        byte [] bytes = null;
        try {
            //TODO : change picture path
            bytes = fileProcess.readFile("C:\\Users\\Oday Amr\\Documents\\IdeaProjects\\" +
                    "course_management_system-model\\Louay Amr.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileMessageFeedback.setFile(bytes);
        fileMessageFeedback.setFileExtension("Louay Amr.png");
        fileMessageFeedback.setCourseFeedback(courseFeedback);
        fileMessageFeedback.setPostMessage("messageFile Feedback");

        this.feedbackService.createFileMessageFeedback(fileMessageFeedback);

        System.out.println(fileMessageFeedback);
    }

    @Test
    public void testCase09_update_feedbackFileMessage(){
        CourseFeedback courseFeedback = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback.setFeedbackID((long) 3);
        courseFeedback = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        System.out.println(courseFeedback);

        FileMessageFeedback fileMessageFeedback = this.applicationContext.getBean(FileMessageFeedback.class);
        fileMessageFeedback.setCourseFeedback(courseFeedback);
        fileMessageFeedback = this.feedbackService.findFileMessageFeedbackByFeedbackId(fileMessageFeedback);

        FileProcess fileProcess = this.applicationContext.getBean(FileProcess.class);
        byte [] bytes = null;
        try {
            //TODO : change picture path
            bytes = fileProcess.readFile("C:\\Users\\Oday Amr\\Documents\\IdeaProjects\\" +
                    "course_management_system-model\\Louay Amr_black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileMessageFeedback.setFile(bytes);
        fileMessageFeedback.setFileExtension("Louay Amr_black.png");
        fileMessageFeedback.setPostMessage("update fileMessage Feedback");

        this.feedbackService.updateFileMessageFeedback(fileMessageFeedback);

        System.out.println(fileMessageFeedback);
    }

    @Test
    public void testCase10_delete_feedbackContent_and_courseFeedback(){
        CourseFeedback courseFeedback1 = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback1.setFeedbackID((long) 1);
        courseFeedback1 = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback1);

        System.out.println(courseFeedback1);

        CourseFeedback courseFeedback2 = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback2.setFeedbackID((long) 2);
        courseFeedback2 = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback2);

        System.out.println(courseFeedback2);

        CourseFeedback courseFeedback3 = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback3.setFeedbackID((long) 3);
        courseFeedback3 = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback3);

        System.out.println(courseFeedback3);

        this.feedbackService.deleteCourseFeedbackByFeedbackId(courseFeedback1);
        this.feedbackService.deleteCourseFeedbackByFeedbackId(courseFeedback2);
        this.feedbackService.deleteCourseFeedbackByFeedbackId(courseFeedback3);
    }

    @Test
    public void testCase11_find_and_delete_course() {
        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long)1);

        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        courseService.deleteCourseByCourseId(courses);
    }

    @Test
    public void testCase12_find_and_delete_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("feedback@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);

        System.out.println(admin);

        accountService.deleteAccountByEmail(admin);
    }
}
