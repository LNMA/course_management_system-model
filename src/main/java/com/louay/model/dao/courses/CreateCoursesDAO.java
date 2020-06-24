package com.louay.model.dao.courses;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.courses.members.CourseMembers;
import com.louay.model.chains.courses.members.UsersAttendance;

public interface CreateCoursesDAO {

    int createCourses(Courses course);

    int createCourseMembers(CourseMembers courseMembers);

    int createStudentsAttendances(UsersAttendance usersAttendance);
}
