package com.louay.model.service.course;

import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.wrapper.GeneralSearch;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CourseService {
    Courses createCourse(Courses courses);

    Courses deleteCourseByCourseId(Courses courses);

    Courses updateCourse(Courses courses);

    Courses findCourseByCourseId(Courses courses);

    Collection<Courses> findAllCourseByCourseId(Iterable<Courses> courses);

    List<Courses> findAllCoursePagination(int pageNumber, int pageSize);

    Long getCoursesCountRow();

    Set<Courses> findCourseLikeForSearch(GeneralSearch generalSearch);

    Long getCountCourseLikeForSearch(GeneralSearch generalSearch);

    Set<Courses> findCourseByInstructorId(Courses courses);
}
