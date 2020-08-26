package com.louay.model.test_case;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeedbackTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    @Autowired
    private FeedbackService feedbackService;

    @Before
    public void initialize01_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Test
    public void testCase01_create_instructor() {
        Admin admin = new Admin();
        admin.setEmail("feedback@test.com");
        admin.setPassword("1234");

        Instructor instructor = new Instructor();
        instructor.setAdmin(admin);
        instructor.setForename("Feedback");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 22);
        instructor.setBirthday(calendar);
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone("875454");
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
    public void testCase02_create_course() {
        Instructor instructor = new Instructor();
        instructor.setEmail("feedback@test.com");
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
    public void testCase03_create_feedbackMessage() {
        Users users = new Users();
        users.setEmail("feedback@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        System.out.println(users);

        Courses courses = new Courses();
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setCourse(courses);
        courseFeedback.setUser(users);

        System.out.println(courseFeedback);

        MessageFeedback messageFeedback = new MessageFeedback();
        messageFeedback.setCourseFeedback(courseFeedback);
        messageFeedback.setPostMessage("This feedback is for test only.");

        this.feedbackService.createMessageFeedback(messageFeedback);
        System.out.println(messageFeedback);
    }

    @Test
    public void testCase04_update_feedbackMessage(){
        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setFeedbackID((long) 1);
        courseFeedback = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        System.out.println(courseFeedback);

        MessageFeedback messageFeedback = new MessageFeedback();
        messageFeedback.setCourseFeedback(courseFeedback);
        messageFeedback = this.feedbackService.findMessageFeedbackByFeedbackId(messageFeedback);
        messageFeedback.setPostMessage("new update feedback");

        System.out.println(messageFeedback);

        this.feedbackService.updateMessageFeedback(messageFeedback);

        System.out.println(messageFeedback);

    }

    @Test
    public void testCase05_create_feedbackFile(){
        Users users = new Users();
        users.setEmail("feedback@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        System.out.println(users);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setCourse(courses);
        courseFeedback.setUser(users);

        System.out.println(courseFeedback);

        FileFeedback fileFeedback = new FileFeedback();

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
    public void testCase06_update_feedbackFile(){
        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setFeedbackID((long) 2);
        courseFeedback = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        System.out.println(courseFeedback);

        FileFeedback fileFeedback = new FileFeedback();
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
    public void testCase07_create_feedbackFileMessage(){
        Users users = new Users();
        users.setEmail("feedback@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setCourse(courses);
        courseFeedback.setUser(users);

        System.out.println(courseFeedback);

        FileMessageFeedback fileMessageFeedback = new FileMessageFeedback();

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
    public void testCase08_update_feedbackFileMessage(){
        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setFeedbackID((long) 3);
        courseFeedback = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        System.out.println(courseFeedback);

        FileMessageFeedback fileMessageFeedback = new FileMessageFeedback();
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
    public void testCase09_delete_feedbackContent_and_courseFeedback(){
        CourseFeedback courseFeedback1 = new CourseFeedback();
        courseFeedback1.setFeedbackID((long) 1);
        courseFeedback1 = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback1);

        System.out.println(courseFeedback1);

        CourseFeedback courseFeedback2 = new CourseFeedback();
        courseFeedback2.setFeedbackID((long) 2);
        courseFeedback2 = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback2);

        System.out.println(courseFeedback2);

        CourseFeedback courseFeedback3 = new CourseFeedback();
        courseFeedback3.setFeedbackID((long) 3);
        courseFeedback3 = this.feedbackService.findCourseFeedbackByFeedbackId(courseFeedback3);

        System.out.println(courseFeedback3);

        this.feedbackService.deleteCourseFeedbackByFeedbackId(courseFeedback1);
        this.feedbackService.deleteCourseFeedbackByFeedbackId(courseFeedback2);
        this.feedbackService.deleteCourseFeedbackByFeedbackId(courseFeedback3);
    }

    @Test
    public void testCase10_find_and_delete_course() {
        Courses courses = new Courses();
        courses.setCourseID((long)1);

        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        courseService.deleteCourseByCourseId(courses);
    }

    @Test
    public void testCase11_find_and_delete_account() {
        Instructor instructor = new Instructor();
        instructor.setEmail("feedback@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        System.out.println(instructor);

        accountService.deleteInstructorsDetailsByInstructorID(instructor);
    }
}
