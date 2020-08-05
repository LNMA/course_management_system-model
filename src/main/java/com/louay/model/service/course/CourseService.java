package com.louay.model.service.course;

import com.louay.model.entity.courses.Courses;

public interface CourseService {
    Courses createCourse(Courses courses);

    Courses deleteCourseByCourseId(Courses courses);

    Courses updateCourse(Courses courses);

    Courses findCourseByCourseId(Courses courses);
}
