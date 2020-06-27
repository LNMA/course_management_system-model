package com.louay.model.chains.feedback;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.Users;
import com.louay.model.chains.users.constant.UserRole;
import com.louay.model.chains.users.factory.UserFactoryProducer;

import java.sql.Timestamp;
import java.util.Objects;

public abstract class Feedback implements Comparable<Feedback> {
    private Long feedbackID;
    private Courses course;
    private final UserFactoryProducer userFactoryProducer;
    private Users user;
    private java.sql.Timestamp feedbackDate;

    public Feedback(Courses course, UserFactoryProducer userFactoryProducer) {
        this.course = course;
        this.userFactoryProducer = userFactoryProducer;
    }

    public Long getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Long feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
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

    public void setUserInstance(UserRole userRole){
        this.user = this.userFactoryProducer.getFactory(userRole).getUsers();
    }

    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Timestamp feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    @Override
    public int compareTo(Feedback o) {
        return this.getFeedbackID().compareTo(o.getFeedbackID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return getFeedbackID().equals(feedback.getFeedbackID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackID());
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackID=" + feedbackID +
                ", course=" + course +
                ", user=" + user +
                ", feedbackDate=" + feedbackDate +
                '}';
    }
}
