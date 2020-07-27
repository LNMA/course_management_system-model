package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.courses.members.CourseMembers;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
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
    public void testCase01_create_courseMember() {
        Student student = this.applicationContext.getBean(Student.class);
        student.setEmail("louay@test.com");
        student.setForename("louay");
        student.setSurname("Amr");

        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("louay@test.com");
        instructor.setForename("louay");
        instructor.setSurname("Amr");
        instructor.setProfileVisibility(InstructorProfileVisibility.PUBLIC);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 8);
        courses.setCourseName("math");
        courses.setStartDate(java.sql.Date.valueOf("2020-07-26"));
        courses.setEndDate(java.sql.Date.valueOf("2020-07-27"));
        courses.setInstructor(instructor);

        CourseMembers courseMembers = this.applicationContext.getBean(CourseMembers.class);
        courseMembers.setStudent(student);
        courseMembers.setCourse(courses);

        this.courseMemberService.createCourseMember(courseMembers);
    }

    @Test
    public void testCase0_find_and_update_courseMember() {
        CourseMembers courseMembers = this.applicationContext.getBean(CourseMembers.class);
        courseMembers.setMemberId((long) 1);

        courseMembers = this.courseMemberService.findMemberByMemberId(courseMembers);
        System.out.println(courseMembers);

    }

}
