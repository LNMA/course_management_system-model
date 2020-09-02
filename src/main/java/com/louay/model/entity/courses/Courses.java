package com.louay.model.entity.courses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.louay.model.entity.users.Instructor;
import org.hibernate.annotations.LazyGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "courses", indexes = {@Index(name = "courses_instructor_id_IX", columnList = "instructor_id")})
public class Courses implements Serializable, Comparable<Courses> {
    private static final long serialVersionUID = -8014117428964625972L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false, columnDefinition = "BIGINT(20)")
    private Long courseID;

    @Column(name = "course_name", length = 150, nullable = false, columnDefinition = "VARCHAR(150)")
    private String courseName;

    @JsonIgnore
    @Lob
    @Column(name = "course_picture", columnDefinition = "LONGBLOB", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    @LazyGroup("lobs")
    private byte[] picture;

    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP(0)")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startDate;

    @Column(name = "end_date", nullable = false, columnDefinition = "TIMESTAMP(0)")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endDate;

    @ManyToOne(targetEntity = Instructor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructors_id", nullable = false, foreignKey =
    @ForeignKey(name = "fk_instructors_details_id_courses_instructor_id", foreignKeyDefinition = "FOREIGN KEY " +
            "(instructor_id) REFERENCES instructors_details (instructors_id) ON DELETE CASCADE ON UPDATE CASCADE"),
            columnDefinition = "VARCHAR(200)")
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Calendar getStartDate() {
        return this.startDate;
    }

    public String getStartDateString() {
        return this.startDate.getTime().toString();
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return this.endDate;
    }

    public String getEndDateString() {
        return this.endDate.getTime().toString();
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public StringBuilder getCoursePictureBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.picture));

        return stringBase46;
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
                ", picture='" + Arrays.toString(this.picture) + '\'' +
                ", startDate=" + this.startDate +
                ", endDate=" + this.endDate +
                ", instructor=" + this.instructor.getEmail() +
                '}';
    }
}
