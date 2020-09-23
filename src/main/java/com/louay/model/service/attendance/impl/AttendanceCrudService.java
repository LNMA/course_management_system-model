package com.louay.model.service.attendance.impl;

import com.louay.model.dao.attendance.UsersAttendanceDao;
import com.louay.model.entity.courses.members.UsersAttendance;
import com.louay.model.entity.wrapper.StudentAttendanceReport;
import com.louay.model.service.attendance.UsersAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class AttendanceCrudService implements UsersAttendanceService, Serializable {
    private static final long serialVersionUID = -6998481276666681382L;
    private final UsersAttendanceDao usersAttendanceDao;

    @Autowired
    public AttendanceCrudService(UsersAttendanceDao usersAttendanceDao) {
        if (usersAttendanceDao == null){
            throw new IllegalArgumentException("DAO cannot be null at AttendanceCrudService.class");
        }
        this.usersAttendanceDao = usersAttendanceDao;
    }

    private UsersAttendanceDao getUsersAttendanceDao() {
        return usersAttendanceDao;
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

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public List<UsersAttendance> findUsersAttendanceByCourseAndDate(StudentAttendanceReport studentAttendanceReport) {
        return getUsersAttendanceDao().findUsersAttendanceByCourseAndDate(studentAttendanceReport);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public List<UsersAttendance> findUsersAttendanceByCourse(UsersAttendance usersAttendance) {
        return getUsersAttendanceDao().findUsersAttendanceByCourse(usersAttendance);
    }
}
