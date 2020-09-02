package com.louay.model.entity.feedback.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.louay.model.entity.feedback.CourseFeedback;
import com.louay.model.entity.users.Users;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "feedback_comments", indexes = {
        @Index(name = "feedback_comments_feedback_id_IX", columnList = "feedback_id"),
        @Index(name = "feedback_comments_user_id_IX", columnList = "user_id")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"commentDate"}, allowGetters = true)
public class Comment implements Comparable<Comment>, Serializable {
    private static final long serialVersionUID = 1789588317346462032L;
    @Id
    @Column(name = "comment_id", nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CourseFeedback.class)
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_course_feedback_id_feedback_comments_id", foreignKeyDefinition = "FOREIGN KEY (feedback_id) " +
            "REFERENCES course_feedback (feedback_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private CourseFeedback courseFeedback;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_feedback_comments_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users user;

    @Column(name = "comment_message", length = 200, nullable = false, columnDefinition = "VARCHAR(600)")
    private String commentMessage;

    @Column(name = "comment_date", nullable = false, columnDefinition = "TIMESTAMP(0)")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Calendar commentDate;

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

    public Calendar getCommentDate() {
        return commentDate;
    }

    public String getCommentDateString() {
        return this.commentDate.getTime().toString();
    }

    public void setCommentDate(Calendar commentDate) {
        this.commentDate = commentDate;
    }

    @Transient
    @Override
    public int compareTo(Comment o) {
        if (this.commentDate == null || o.getCommentDate() == null){
            return 0;
        }
        if (this.getCommentDate().after(o.commentDate)){
            return 1;
        }
        if (this.getCommentDate().before(o.commentDate)){
            return -1;
        }
        return 0;
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
                ", commentDate=" + commentDate.getTime().toString() +
                '}';
    }
}