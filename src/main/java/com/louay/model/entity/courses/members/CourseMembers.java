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
@Table(schema = "course_management_system", name = "course_members")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"registerDate"}, allowGetters = true)
public class CourseMembers implements Serializable, Comparable<CourseMembers> {
    private static final long serialVersionUID = 7079422481851662405L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Student.class)
    @JoinColumn(name = "user_id", referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Courses.class)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Courses course;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "register_date")
    private Date registerDate;

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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public int compareTo(CourseMembers o) {
        return this.memberId.compareTo(o.getMemberId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseMembers that = (CourseMembers) o;
        return getMemberId().equals(that.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId());
    }

    @Override
    public String toString() {
        return "CourseMembers{" +
                "memberId=" + memberId +
                ", student=" + student +
                ", course=" + course +
                ", registerDate=" + registerDate +
                '}';
    }
}
