package com.louay.model.entity.courses.members;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.users.Student;
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
@Table(name = "students_attendances", indexes = {
        @Index(name = "students_attendances_student_id_IX", columnList = "student_id"),
        @Index(name = "students_attendances_course_id_IX", columnList = "course_id")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"attendanceDate"}, allowGetters = true)
public class UsersAttendance implements Serializable, Comparable<UsersAttendance> {
    private static final long serialVersionUID = 4153260012886912057L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id", columnDefinition = "BIGINT(20)", nullable = false)
    private Long attendancesId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_students_details_id_attendances_student_id", foreignKeyDefinition = "FOREIGN KEY (student_id)" +
            " REFERENCES students_details (student_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Courses.class)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_courses_course_id_students_attendances_course_id", foreignKeyDefinition = "FOREIGN KEY " +
            "(course_id) REFERENCES courses (course_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Courses course;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "attendance_date", columnDefinition = "TIMESTAMP(0)")
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

    @Transient
    @Override
    public int compareTo(UsersAttendance o) {
        return this.attendancesId.compareTo(o.getAttendancesId());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAttendance that = (UsersAttendance) o;
        return getAttendancesId().equals(that.getAttendancesId());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getAttendancesId());
    }

    @Transient
    @Override
    public String toString() {
        return "UsersAttendance{" +
                "attendancesId=" + attendancesId +
                ", student=" + student.getEmail() +
                ", course=" + course.getCourseID() +
                ", attendanceDate=" + attendanceDate +
                '}';
    }
}
