package com.louay.model.dao.courses;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.courses.members.CourseMembers;
import com.louay.model.chains.courses.members.UsersAttendance;

public interface UpdateCoursesDAO {

    int updateCoursesByCourseID(Courses course);

    int updateCourseMembersByCourseID(CourseMembers courseMembers);

    int updateStudentsAttendancesByUserID(UsersAttendance usersAttendance);
}
