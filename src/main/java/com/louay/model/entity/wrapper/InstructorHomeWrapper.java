package com.louay.model.entity.wrapper;

import com.louay.model.entity.users.Instructor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class InstructorHomeWrapper implements Serializable, Comparable<InstructorHomeWrapper> {
    private static final long serialVersionUID = 6532151586190040579L;
    private Instructor instructor;
    private Calendar lastSignInDate;

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Calendar getLastSignInDate() {
        return lastSignInDate;
    }

    public void setLastSignInDate(Calendar lastSignInDate) {
        this.lastSignInDate = lastSignInDate;
    }

    public String getTimeBirthday() {
        return this.instructor.getBirthday().getTime().toString();
    }

    public String getTimeJoinDate() {
        return this.instructor.getAdmin().getJoinDate().getTime().toString();
    }

    @Override
    public int compareTo(InstructorHomeWrapper o) {
        return this.instructor.compareTo(o.getInstructor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructorHomeWrapper that = (InstructorHomeWrapper) o;
        return getInstructor().equals(that.getInstructor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstructor());
    }

    @Override
    public String toString() {
        return "InstructorHomeWrapper{" +
                "instructor=" + instructor +
                ", lastSignInDate=" + lastSignInDate +
                '}';
    }
}
