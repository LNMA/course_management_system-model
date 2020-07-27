package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.course.CourseService;
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
    public void testCase01_create_course() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("louay@test.com");
        instructor.setForename("louay");
        instructor.setSurname("Amr");
        instructor.setProfileVisibility(InstructorProfileVisibility.PUBLIC);


        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseName("math");
        courses.setStartDate(java.sql.Date.valueOf("2020-07-26"));
        courses.setEndDate(java.sql.Date.valueOf("2020-07-27"));
        courses.setInstructor(instructor);

        this.courseService.createCourse(courses);
    }

    @Test
    public void testCase02_find_and_update_course() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("louay@test.com");
        instructor.setProfileVisibility(InstructorProfileVisibility.PUBLIC);


        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long)7);
        courses.setCourseName("math");
        courses.setStartDate(java.sql.Date.valueOf("2020-07-26"));
        courses.setEndDate(java.sql.Date.valueOf("2020-07-27"));
        courses.setInstructor(instructor);

        courses = this.courseService.findCourseByCourseId(courses);

        System.out.println(courses);
        courses.setCourseName("science");

        this.courseService.updateCourse(courses);
    }

    @Test
    public void testCase03_find_and_delete_course() {
        Instructor instructor = this.applicationContext.getBean("instructor",Instructor.class);
        instructor.setEmail("louay@test.com");
        instructor.setProfileVisibility(InstructorProfileVisibility.PUBLIC);


        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long)7);
        courses.setCourseName("math");
        courses.setStartDate(java.sql.Date.valueOf("2020-07-26"));
        courses.setEndDate(java.sql.Date.valueOf("2020-07-27"));
        courses.setInstructor(instructor);

        courses = this.courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        this.courseService.deleteCourseByCourseId(courses);
    }

}
