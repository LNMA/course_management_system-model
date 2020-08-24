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
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "course_members", indexes = {
        @Index(name = "course_members_course_id_IX", columnList = "course_id"),
        @Index(name = "course_members_student_id_IX", columnList = "student_id")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"registerDate"}, allowGetters = true)
public class CourseMembers implements Serializable, Comparable<CourseMembers> {
    private static final long serialVersionUID = 9064521402411668834L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", columnDefinition = "BIGINT(20)", nullable = false)
    private Long memberId;

    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_students_details_id_course_members_student_id", foreignKeyDefinition = "FOREIGN KEY (student_" +
            "id) REFERENCES students_details (student_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Student student;

    @ManyToOne(targetEntity = Courses.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_courses_course_id_course_members_course_id", foreignKeyDefinition = "FOREIGN KEY (course_id)" +
            "REFERENCES courses (course_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Courses course;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "register_date", columnDefinition = "TIMESTAMP(0)", nullable = false)
    private Calendar registerDate;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

    @Transient
    @Override
    public int compareTo(CourseMembers o) {
        return this.memberId.compareTo(o.getMemberId());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseMembers that = (CourseMembers) o;
        return getMemberId().equals(that.getMemberId());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getMemberId());
    }

    @Transient
    @Override
    public String toString() {
        return "CourseMembers{" +
                "memberId=" + memberId +
                ", student=" + student.getEmail() +
                ", course=" + course.getCourseID() +
                ", registerDate=" + registerDate +
                '}';
    }
}
