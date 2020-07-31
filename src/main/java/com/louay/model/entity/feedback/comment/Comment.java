package com.louay.model.entity.feedback.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.feedback.CourseFeedback;
import com.louay.model.entity.users.Users;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "feedback_comments", schema = "course_management_system")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"commentDate"}, allowGetters = true)
public class Comment implements Comparable<Comment>, Serializable {
    private static final long serialVersionUID = -2277738152445132714L;
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CourseFeedback.class)
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id")
    private CourseFeedback courseFeedback;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @Column(name = "comment_message", length = 200)
    private StringBuilder commentMessage;

    @Column(name = "comment_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date commentDate;

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public CourseFeedback getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CourseFeedback courseFeedback) {
        this.courseFeedback = courseFeedback;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public StringBuilder getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(StringBuilder commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Transient
    @Override
    public int compareTo(Comment o) {
        return this.commentID.compareTo(o.getCommentID());
    }

    @Transient
    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", courseFeedback=" + courseFeedback.getFeedbackID() +
                ", user=" + user.getEmail() +
                ", commentMessage=" + commentMessage +
                ", commentDate=" + commentDate +
                '}';
    }
}
