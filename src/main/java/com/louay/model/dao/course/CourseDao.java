package com.louay.model.dao.course;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.wrapper.GeneralSearch;

import java.util.List;
import java.util.Set;

public interface CourseDao extends CommonDao<Courses>, GenericDao<Courses> {
    List<Courses> findAllCourse(int pageNumber, int pageSize);

    Long getCountRow();

    Set<Courses> findCourseLikePagination(GeneralSearch generalSearch);

    Long getCountCourseLikePagination(GeneralSearch generalSearch);
}
