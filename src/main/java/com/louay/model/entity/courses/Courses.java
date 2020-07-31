package com.louay.model.entity.courses;

import com.louay.model.entity.users.Instructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(schema = "course_management_system", name = "courses")
public class Courses implements Comparable<Courses>, Serializable {
    private static final long serialVersionUID = 6725739113423353373L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseID;

    @Column(name = "course_name", length = 150, nullable = false)
    private String courseName;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne(targetEntity = Instructor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructors_id")
    private Instructor instructor;

    public Long getCourseID() {
        return this.courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Transient
    @Override
    public int compareTo(Courses o) {
        return this.courseID.compareTo(o.getCourseID());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return getCourseID().equals(courses.getCourseID());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getCourseID());
    }

    @Transient
    @Override
    public String toString() {
        return "Courses{" +
                "courseID=" + this.courseID +
                ", courseName='" + this.courseName + '\'' +
                ", startDate=" + this.startDate +
                ", endDate=" + this.endDate +
                ", instructor=" + this.instructor.getEmail() +
                '}';
    }
}
