package com.louay.model.dao.course;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.courses.Courses;

import java.util.List;

public interface CourseDao extends CommonDao<Courses>, GenericDao<Courses> {
    List<Courses> findAllCourse(int pageNumber, int pageSize);

    Long getCountRow();
}
