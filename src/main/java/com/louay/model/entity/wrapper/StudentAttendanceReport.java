package com.louay.model.entity.wrapper;

import com.louay.model.entity.courses.members.UsersAttendance;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class StudentAttendanceReport implements Serializable, Comparable<StudentAttendanceReport> {
    private static final long serialVersionUID = 9138998701182147471L;
    UsersAttendance usersAttendance;
    Calendar fromDate;
    Calendar toDate;

    public UsersAttendance getUsersAttendance() {
        return usersAttendance;
    }

    public void setUsersAttendance(UsersAttendance usersAttendance) {
        this.usersAttendance = usersAttendance;
    }

    public Calendar getFromDate() {
        return fromDate;
    }

    public void setFromDate(Calendar fromDate) {
        this.fromDate = fromDate;
    }

    public Calendar getToDate() {
        return toDate;
    }

    public void setToDate(Calendar toDate) {
        this.toDate = toDate;
    }

    @Override
    public int compareTo(StudentAttendanceReport o) {
        return this.usersAttendance.compareTo(o.getUsersAttendance());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAttendanceReport that = (StudentAttendanceReport) o;
        return getUsersAttendance().equals(that.getUsersAttendance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsersAttendance());
    }

    @Override
    public String toString() {
        return "StudentAttendanceReport{" +
                "usersAttendance=" + usersAttendance +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
