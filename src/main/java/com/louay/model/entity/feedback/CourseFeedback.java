package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.users.Users;
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
@Table(name = "course_feedback", indexes = {
        @Index(name = "course_file_feedback_course_id_IX", columnList = "course_id"),
        @Index(name = "course_feedback_user_id_IX", columnList = "user_id")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"feedbackDate"}, allowGetters = true)
public class CourseFeedback implements Comparable<CourseFeedback>, Serializable {
    private static final long serialVersionUID = -7182542334355434557L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false, columnDefinition = "BIGINT(20)")
    private Long feedbackID;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Courses.class)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_courses_course_id_course_file_feedback_course_id", foreignKeyDefinition = "FOREIGN KEY " +
            "(course_id) REFERENCES courses (course_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Courses course;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_course_feedback_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"),nullable = false)
    private Users user;

    @Column(name = "feedback_date", columnDefinition = "TIMESTAMP(0)", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date feedbackDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "courseFeedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private FeedbackContent feedbackContent;

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

    public FeedbackContent getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(FeedbackContent feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    @Override
    public int compareTo(CourseFeedback o) {
        return this.feedbackID.compareTo(o.getFeedbackID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseFeedback that = (CourseFeedback) o;
        return getFeedbackID().equals(that.getFeedbackID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackID());
    }

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


