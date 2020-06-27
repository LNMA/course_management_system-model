package com.louay.model.dao.courses;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.courses.members.CourseMembers;
import com.louay.model.chains.courses.members.UsersAttendance;

public interface DeleteCoursesDAO {

    int deleteCoursesByCourseID(Courses course);

    int deleteCourseMembersByCourseID(CourseMembers courseMembers);

    int deleteStudentsAttendancesByUserID(UsersAttendance usersAttendance);
}
