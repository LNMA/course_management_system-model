package com.louay.model.dao.courses;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.courses.members.CourseMembers;
import com.louay.model.chains.courses.members.UsersAttendance;

public interface UpdateCoursesDAO {

    int updateCourses(Courses course);

    int updateCourseMembers(CourseMembers courseMembers);

    int updateStudentsAttendances(UsersAttendance usersAttendance);
}
