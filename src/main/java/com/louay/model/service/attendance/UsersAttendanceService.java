package com.louay.model.service.attendance;

import com.louay.model.entity.courses.members.UsersAttendance;
import com.louay.model.entity.wrapper.StudentAttendanceReport;

import java.util.List;

public interface UsersAttendanceService {
    UsersAttendance createUsersAttendance(UsersAttendance usersAttendance);

    UsersAttendance deleteUsersAttendanceByAttendanceId(UsersAttendance usersAttendance);

    UsersAttendance updateUsersAttendance(UsersAttendance usersAttendance);

    UsersAttendance findUsersAttendanceByAttendanceId(UsersAttendance usersAttendance);

    List<UsersAttendance> findUsersAttendanceByCourseAndDate(StudentAttendanceReport studentAttendanceReport);

    List<UsersAttendance> findUsersAttendanceByCourse(UsersAttendance usersAttendance);
}
