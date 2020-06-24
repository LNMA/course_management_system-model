package com.louay.model;

import com.louay.model.chains.courses.members.CourseMembers;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.louay.model");
        applicationContext.refresh();
        CourseMembers courseMembers = applicationContext.getBean(CourseMembers.class);
        courseMembers.setUserInstance(true);
        System.out.println(courseMembers.getUser().getClass());
    }
}
