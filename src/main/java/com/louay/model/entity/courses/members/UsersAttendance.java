package com.louay.model.entity.courses.members;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Student;
import com.louay.model.entity.users.Users;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(schema = "course_management_system", name = "students_attendances")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"attendanceDate"}, allowGetters = true)
public class UsersAttendance implements Serializable, Comparable<UsersAttendance> {
    private static final long serialVersionUID = 4153260012886912057L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendancesId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Courses.class)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Courses course;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "attendance_date")
    private Date attendanceDate;

    public Long getAttendancesId() {
        return attendancesId;
    }

    public void setAttendancesId(Long attendancesId) {
        this.attendancesId = attendancesId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    @Override
    public int compareTo(UsersAttendance o) {
        return this.attendancesId.compareTo(o.getAttendancesId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAttendance that = (UsersAttendance) o;
        return getAttendancesId().equals(that.getAttendancesId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttendancesId());
    }

    @Override
    public String toString() {
        return "UsersAttendance{" +
                "attendancesId=" + attendancesId +
                ", student=" + student +
                ", course=" + course +
                ", attendanceDate=" + attendanceDate +
                '}';
    }
}
