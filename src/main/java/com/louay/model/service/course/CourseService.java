package com.louay.model.service.course;

import com.louay.model.entity.courses.Courses;

import java.util.Collection;
import java.util.List;

public interface CourseService {
    Courses createCourse(Courses courses);

    Courses deleteCourseByCourseId(Courses courses);

    Courses updateCourse(Courses courses);

    Courses findCourseByCourseId(Courses courses);

    Collection<Courses> findAllCourseByCourseId(Iterable<Courses> courses);

    List<Courses> findAllCoursePagination(int pageNumber, int pageSize);

    Long getCoursesCountRow();
}
