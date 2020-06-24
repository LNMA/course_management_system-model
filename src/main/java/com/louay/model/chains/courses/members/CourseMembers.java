package com.louay.model.chains.courses.members;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.Users;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class CourseMembers {
    private UserFactoryProducer userFactoryProducer;
    private Users user;
    private Courses course;
    private java.sql.Timestamp registerDate;

    @Autowired
    public CourseMembers(UserFactoryProducer userFactoryProducer, Courses course) {
        this.userFactoryProducer = userFactoryProducer;
        this.course = course;

    }

    public Users getUser() {
        if (this.user == null){
            throw new RuntimeException("User must be initialized by using setUserInstance() method.");
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUserInstance(boolean student){
        this.user = this.userFactoryProducer.getFactory(student).getUsers();
    }

    public void setFactoryProducer(UserFactoryProducer producer) {
        this.userFactoryProducer = producer;
    }

    public UserFactoryProducer getUserFactoryProducer() {
        return userFactoryProducer;
    }

    public void setUserFactoryProducer(UserFactoryProducer userFactoryProducer) {
        this.userFactoryProducer = userFactoryProducer;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseMembers that = (CourseMembers) o;
        return getUser().equals(that.getUser()) &&
                getCourse().equals(that.getCourse()) &&
                getRegisterDate().compareTo(that.getRegisterDate()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getCourse(), getRegisterDate());
    }

    @Override
    public String toString() {
        return "CourseMembers{" +
                "user=" + user +
                ", course=" + course +
                ", registerDate=" + registerDate +
                '}';
    }
}
