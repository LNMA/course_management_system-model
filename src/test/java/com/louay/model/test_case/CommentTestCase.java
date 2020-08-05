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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private CommentService commentService;


    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_commentService() {
        this.commentService = this.applicationContext.getBean(CommentService.class);
    }

    @Test
    public void testCase01_create_instructor() {
        AccountService accountService = this.applicationContext.getBean(AccountService.class);

        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("comment@test.com");
        admin.setPassword("1234");

        Instructor instructor = this.applicationContext.getBean("instructor", Instructor.class);
        instructor.setAdmin(admin);
        instructor.setForename("Comment");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        instructor.setBirthday(Date.valueOf("2020-07-26"));
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone(875454);
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
        Instructor instructor = this.applicationContext.getBean("instructor", Instructor.class);
        instructor.setEmail("comment@test.com");
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
    public void testCase03_create_feedbackMessage() {
        Users users = this.applicationContext.getBean("users", Users.class);
        users.setEmail("comment@test.com");
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

        CourseFeedback courseFeedback = this.applicationContext.getBean(CourseFeedback.class);
        courseFeedback.setFeedbackID((long) 1);
        FeedbackService feedbackService = this.applicationContext.getBean(FeedbackService.class);
        courseFeedback = feedbackService.findCourseFeedbackByFeedbackId(courseFeedback);

        Comment comment = this.applicationContext.getBean(Comment.class);
        comment.setCourseFeedback(courseFeedback);
        comment.setUser(users);
        comment.setCommentMessage("This is test comment.");

        this.commentService.createComment(comment);

        System.out.println(comment);
    }

    @Test
    public void testCase05_update_comment() {
        Comment comment = this.applicationContext.getBean(Comment.class);
        comment.setCommentID((long) 1);
        comment = this.commentService.findCommentByCommentId(comment);

        comment.setCommentMessage("This is Update comment.");

        this.commentService.updateComment(comment);

        System.out.println(comment);
    }

    @Test
    public void testCase06_delete_comment() {
        Comment comment = this.applicationContext.getBean(Comment.class);
        comment.setCommentID((long) 1);
        comment = this.commentService.findCommentByCommentId(comment);

        this.commentService.updateComment(comment);

        System.out.println(comment);
    }

    @Test
    public void testCase07_find_and_delete_course() {
        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long)1);

        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        courseService.deleteCourseByCourseId(courses);
    }

    @Test
    public void testCase08_find_and_delete_account() {
        Instructor instructor = this.applicationContext.getBean("instructor", Instructor.class);
        instructor.setEmail("comment@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        System.out.println(instructor);

        accountService.deleteInstructorsDetailsByInstructorID(instructor);
    }

}
