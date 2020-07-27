package com.louay.model.service.attendance;

import com.louay.model.entity.courses.members.UsersAttendance;

public interface UsersAttendanceService {
    UsersAttendance createUsersAttendance(UsersAttendance usersAttendance);

    UsersAttendance deleteUsersAttendanceByAttendanceId(UsersAttendance usersAttendance);

    UsersAttendance updateUsersAttendance(UsersAttendance usersAttendance);

    UsersAttendance findUsersAttendanceByAttendanceId(UsersAttendance usersAttendance);
}
