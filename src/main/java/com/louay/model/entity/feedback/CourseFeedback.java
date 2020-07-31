package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.feedback.constant.FeedbackType;
import com.louay.model.entity.users.Users;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "course_feedback")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"feedbackDate"}, allowGetters = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Polymorphism(type = PolymorphismType.EXPLICIT)
public abstract class CourseFeedback implements Comparable<CourseFeedback>, Serializable {
    private static final long serialVersionUID = 5558526425892489363L;
    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackID;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Courses.class)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Courses course;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @Column(name = "feedback_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date feedbackDate;

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
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    @Transient
    abstract public FeedbackType getFeedbackType();

    @Transient
    @Override
    public int compareTo(CourseFeedback o) {
        return this.feedbackID.compareTo(o.getFeedbackID());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseFeedback courseFeedback = (CourseFeedback) o;
        return getFeedbackID().equals(courseFeedback.getFeedbackID());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackID());
    }

    @Transient
    @Override
    public String toString() {
        return "CourseFeedback{" +
                "feedbackID=" + feedbackID +
                ", course=" + course.getCourseID() +
                ", user=" + user.getEmail() +
                ", feedbackDate=" + feedbackDate +
                '}';
    }
}
