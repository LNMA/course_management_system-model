package com.louay.model.service.course.impl;

import com.louay.model.dao.course.CourseDao;
import com.louay.model.entity.courses.Courses;
import com.louay.model.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class CourseCrudService implements CourseService, Serializable {
    private static final long serialVersionUID = 6356106048098627375L;
    private CourseDao courseDao;

    public CourseDao getCourseDao() {
        return courseDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Transactional
    @Override
    public Courses createCourse(Courses courses) {
        return getCourseDao().save(courses);
    }

    @Transactional
    @Override
    public Courses deleteCourseByCourseId(Courses courses) {
        return getCourseDao().delete(courses);
    }

    @Transactional
    @Override
    public Courses updateCourse(Courses courses) {
        return getCourseDao().update(courses);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Courses findCourseByCourseId(Courses courses) {
        return getCourseDao().findOneById(courses);
    }
}
