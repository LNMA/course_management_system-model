package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("student")
@Entity
@Table(schema = "course_management_system", name = "students_details")
public class Student extends Users {
    private static final long serialVersionUID = -8373667447358205274L;
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "headline", length = 300)
    private String headline;

    @Column(name = "interests", length = 300)
    private String interests;

    @Override
    public String getEmail() {
        return this.studentId;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        this.studentId = email;
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
    public Role getUserRole() {
        return Role.STUDENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId);
    }

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentId='" + studentId + '\'' +
                ", headline='" + headline + '\'' +
                ", interests='" + interests + '\'' +
                '}';
    }
}
