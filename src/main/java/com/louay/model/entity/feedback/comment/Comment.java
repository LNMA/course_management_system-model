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
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "feedback_comments")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"commentDate"}, allowGetters = true)
public class Comment implements Comparable<Comment>, Serializable {
    private static final long serialVersionUID = -1203353711305645172L;
    @Id
    @Column(name = "comment_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CourseFeedback.class)
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id", nullable = false)
    private CourseFeedback courseFeedback;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Users user;

    @Column(name = "comment_message", length = 200, nullable = false)
    private String commentMessage;

    @Column(name = "comment_date", nullable = false)
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

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getCommentID().equals(comment.getCommentID());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getCommentID());
    }

    @Transient
    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", courseFeedback=" + courseFeedback.getFeedbackID() +
                ", user=" + user.getEmail() +
                ", commentMessage='" + commentMessage + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}