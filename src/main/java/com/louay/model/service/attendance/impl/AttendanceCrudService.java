package com.louay.model.service.attendance.impl;

import com.louay.model.dao.attendance.UsersAttendanceDao;
import com.louay.model.entity.courses.members.UsersAttendance;
import com.louay.model.service.attendance.UsersAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class AttendanceCrudService implements UsersAttendanceService, Serializable {
    private static final long serialVersionUID = 5411356917719085167L;
    private UsersAttendanceDao usersAttendanceDao;

    public UsersAttendanceDao getUsersAttendanceDao() {
        return usersAttendanceDao;
    }

    @Autowired
    public void setUsersAttendanceDao(UsersAttendanceDao usersAttendanceDao) {
        this.usersAttendanceDao = usersAttendanceDao;
    }

    @Transactional
    @Override
    public UsersAttendance createUsersAttendance(UsersAttendance usersAttendance) {
        return getUsersAttendanceDao().save(usersAttendance);
    }

    @Transactional
    @Override
    public UsersAttendance deleteUsersAttendanceByAttendanceId(UsersAttendance usersAttendance) {
        return getUsersAttendanceDao().delete(usersAttendance);
    }

    @Transactional
    @Override
    public UsersAttendance updateUsersAttendance(UsersAttendance usersAttendance) {
        return getUsersAttendanceDao().update(usersAttendance);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public UsersAttendance findUsersAttendanceByAttendanceId(UsersAttendance usersAttendance) {
        return getUsersAttendanceDao().findOneById(usersAttendance);
    }
}
