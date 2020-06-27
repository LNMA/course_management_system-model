package com.louay.model.chains.users;

import com.louay.model.chains.users.constant.UserRole;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class Student extends Users {
    private String headline;
    private String interests;

    public Student() {
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getHeadline().equals(student.getHeadline()) &&
                getInterests().equals(student.getInterests());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHeadline(), getInterests());
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.STUDENT;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "headline='" + headline + '\'' +
                ", interests='" + interests + '\'' +
                '}';
    }
}
