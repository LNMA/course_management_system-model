package com.louay.model.test_case;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.feedback.CourseFeedback;
import com.louay.model.entity.feedback.MessageFeedback;
import com.louay.model.entity.feedback.comment.Comment;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Users;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.comment.CommentService;
import com.louay.model.service.course.CourseService;
import com.louay.model.service.feedback.FeedbackService;
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
public class CommentTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    @Autowired
    private CommentService commentService;


    @Before
    public void initialize01_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Test
    public void testCase01_create_instructor() {
        AccountService accountService = this.applicationContext.getBean(AccountService.class);

        Admin admin = new Admin();
        admin.setEmail("comment@test.com");
        admin.setPassword("1234");

        Instructor instructor = new Instructor();
        instructor.setAdmin(admin);
        instructor.setForename("Comment");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 22);
        instructor.setBirthday(calendar);
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone("875454");
        instructor.setAddress("CommentTestCase");
        instructor.setHeadline("i am instructor");
        instructor.setNickname("dr");
        instructor.setSpecialty("math");
        instructor.setPortfolio("github");
        instructor.setProfileVisibility(InstructorProfileVisibility.PUBLIC);

        accountService.createInstructorsDetails(instructor);
    }

    @Test
    public void testCase02_create_course() {
        Instructor instructor = new Instructor();
        instructor.setEmail("comment@test.com");
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
        users.setEmail("comment@test.com");
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

        FeedbackService feedbackService = this.applicationContext.getBean(FeedbackService.class);
        feedbackService.createMessageFeedback(messageFeedback);
        System.out.println(messageFeedback);
    }

    @Test
    public void testCase04_create_comment() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("comment@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        CourseFeedback courseFeedback = new CourseFeedback();
        courseFeedback.setFeedbackID((long) 1);
        FeedbackService feedbackService = this.applicationContext.getBean(FeedbackService.class);
        courseFeedback = feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        Comment comment = new Comment();
        comment.setCourseFeedback(courseFeedback);
        comment.setUser(users);
        comment.setCommentMessage("This is test comment.");

        this.commentService.createComment(comment);

        System.out.println(comment);
    }

    @Test
    public void testCase05_update_comment() {
        Comment comment = new Comment();
        comment.setCommentID((long) 1);
        comment = this.commentService.findCommentByCommentId(comment);

        comment.setCommentMessage("This is Update comment.");

        this.commentService.updateComment(comment);

        System.out.println(comment);
    }

    @Test
    public void testCase06_delete_comment() {
        Comment comment = new Comment();
        comment.setCommentID((long) 1);
        comment = this.commentService.findCommentByCommentId(comment);

        this.commentService.updateComment(comment);

        System.out.println(comment);
    }

    @Test
    public void testCase07_find_and_delete_course() {
        Courses courses = new Courses();
        courses.setCourseID((long)1);

        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        courseService.deleteCourseByCourseId(courses);
    }

    @Test
    public void testCase08_find_and_delete_account() {
        Instructor instructor = new Instructor();
        instructor.setEmail("comment@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        System.out.println(instructor);

        accountService.deleteInstructorsDetailsByInstructorID(instructor);
    }

}
