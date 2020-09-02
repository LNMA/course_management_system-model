package com.louay.model.service.course.impl;

import com.louay.model.dao.course.CourseDao;
import com.louay.model.entity.courses.Courses;
import com.louay.model.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class CourseCrudService implements CourseService, Serializable {
    private static final long serialVersionUID = -1643494765546534712L;
    private final CourseDao courseDao;

    @Autowired
    public CourseCrudService(CourseDao courseDao) {
        if (courseDao == null){
            throw new IllegalArgumentException("DAO cannot be null at CourseCrudService.class");
        }
        this.courseDao = courseDao;
    }

    private CourseDao getCourseDao() {
        return courseDao;
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

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Courses findCourseByCourseId(Courses courses) {
        return getCourseDao().findOneById(courses);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Collection<Courses> findAllCourseByCourseId(Iterable<Courses> courses) {
        return getCourseDao().findAllById(courses);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public List<Courses> findAllCoursePagination(int pageNumber, int pageSize) {
        return getCourseDao().findAllCourse(pageNumber, pageSize);
    }

    @Transactional
    @Override
    public Long getCoursesCountRow() {
        return getCourseDao().getCountRow();
    }
}
