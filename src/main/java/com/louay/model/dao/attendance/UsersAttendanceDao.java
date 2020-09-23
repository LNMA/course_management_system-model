package com.louay.model.dao.attendance;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.courses.members.UsersAttendance;
import com.louay.model.entity.wrapper.StudentAttendanceReport;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface UsersAttendanceDao extends CommonDao<UsersAttendance>, GenericDao<UsersAttendance> {
    List<UsersAttendance> findUsersAttendanceByCourseAndDate(StudentAttendanceReport studentAttendanceReport);

    List<UsersAttendance> findUsersAttendanceByCourse(UsersAttendance usersAttendance);

}
