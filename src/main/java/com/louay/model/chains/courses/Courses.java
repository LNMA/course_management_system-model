package com.louay.model.chains.courses;

import com.louay.model.chains.users.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class Courses {
    private Double courseID;
    private String courseName;
    private java.sql.Timestamp startDate;
    private java.sql.Timestamp endDate;
    private Instructor instructor;

    @Autowired
    public Courses(Instructor instructor) {
        this.instructor = instructor;
    }

    public Double getCourseID() {
        return courseID;
    }

    public void setCourseID(Double courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return getCourseID().equals(courses.getCourseID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseID());
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", instructor=" + instructor +
                '}';
    }
}
