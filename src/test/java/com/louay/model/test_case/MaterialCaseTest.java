package com.louay.model.test_case;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.TextMaterials;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Instructor;
import com.louay.model.entity.users.Users;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.course.CourseService;
import com.louay.model.service.material.MaterialService;
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
public class MaterialCaseTest {
    private AnnotationConfigApplicationContext applicationContext;
    @Autowired
    private MaterialService materialService;

    @Before
    public void initialize01_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Test
    public void testCase01_create_instructor() {
        Admin admin = new Admin();
        admin.setEmail("material@test.com");
        admin.setPassword("12345");

        Instructor instructor = new Instructor();
        instructor.setAdmin(admin);
        instructor.setEmail("material@test.com");
        instructor.setForename("Material");
        instructor.setSurname("Test");
        instructor.setGender(Gender.MALE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 22);
        instructor.setBirthday(calendar);
        instructor.setCountry("Test");
        instructor.setState("model");
        instructor.setPhone("875454");
        instructor.setAddress("MaterialCaseTest");
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
        instructor.setEmail("material@test.com");
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
    public void testCase03_create_textMaterial() {
        Users users = new Users();
        users.setEmail("material@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        TextMaterials textMaterials = new TextMaterials();
        textMaterials.setCourse(courses);
        textMaterials.setUser(users);
        textMaterials.setText("this is text material for test only.");
        textMaterials.setMaterialName("Test Material");

        this.materialService.createTextMaterials(textMaterials);

        System.out.println(textMaterials);
    }

    @Test
    public void testCase04_update_textMaterial() {
        TextMaterials textMaterials = new TextMaterials();
        textMaterials.setMaterialID((long) 1);
        textMaterials = this.materialService.findTextMaterialsByMaterialId(textMaterials);

        textMaterials.setText("update text material");

        this.materialService.updateTextMaterials(textMaterials);

        System.out.println(textMaterials);
    }

    @Test
    public void testCase05_create_fileMaterial() {
        Users users = new Users();
        users.setEmail("material@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        users = accountService.findUsersByUserID(users);

        Courses courses = this.applicationContext.getBean(Courses.class);
        courses.setCourseID((long) 1);
        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        FileMaterials fileMaterials = new FileMaterials();
        fileMaterials.setCourse(courses);
        fileMaterials.setUser(users);
        fileMaterials.setMaterialName("Louay Amr.png");

        FileProcess fileProcess = this.applicationContext.getBean(FileProcess.class);
        byte [] bytes = null;
        try {
            //TODO : change picture path
            bytes = fileProcess.readFile("C:\\Users\\Oday Amr\\Documents\\IdeaProjects\\" +
                    "course_management_system-model\\Louay Amr.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileMaterials.setFile(bytes);

        this.materialService.createFileMaterials(fileMaterials);

        System.out.println(fileMaterials);
    }

    @Test
    public void testCase06_update_fileMaterial() {
        FileMaterials fileMaterials = new FileMaterials();
        fileMaterials.setMaterialID((long) 2);
        fileMaterials = this.materialService.findFileMaterialsByMaterialId(fileMaterials);

        fileMaterials.setMaterialName("update material name");

        this.materialService.updateFileMaterials(fileMaterials);

        System.out.println(fileMaterials);
    }

    @Test
    public void testCase07_delete_material() {
        FileMaterials fileMaterials = new FileMaterials();
        fileMaterials.setMaterialID((long) 2);
        fileMaterials = this.materialService.findFileMaterialsByMaterialId(fileMaterials);

        System.out.println(fileMaterials);

        TextMaterials textMaterials = new TextMaterials();
        textMaterials.setMaterialID((long) 1);
        textMaterials = this.materialService.findTextMaterialsByMaterialId(textMaterials);

        System.out.println(textMaterials);

        this.materialService.deleteFileMaterialsByMaterialId(fileMaterials);
        this.materialService.deleteTextMaterialsByMaterialId(textMaterials);
    }


    @Test
    public void testCase08_find_and_delete_course() {
        Courses courses = new Courses();
        courses.setCourseID((long)1);

        CourseService courseService = this.applicationContext.getBean(CourseService.class);
        courses = courseService.findCourseByCourseId(courses);

        System.out.println(courses);

        courseService.deleteCourseByCourseId(courses);
    }

    @Test
    public void testCase09_find_and_delete_account() {
        Instructor instructor = new Instructor();
        instructor.setEmail("material@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        instructor = accountService.findInstructorsDetailsByInstructorID(instructor);

        System.out.println(instructor);

        accountService.deleteInstructorsDetailsByInstructorID(instructor);
    }


}
