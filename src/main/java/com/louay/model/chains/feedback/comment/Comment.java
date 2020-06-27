package com.louay.model.chains.feedback.comment;

import com.louay.model.chains.feedback.Feedback;
import com.louay.model.chains.feedback.constant.FeedbackType;
import com.louay.model.chains.feedback.factory.FeedbackFactoryProducer;
import com.louay.model.chains.users.Users;
import com.louay.model.chains.users.constant.UserRole;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class Comment {
    private Long commentID;
    private Feedback feedback;
    private final FeedbackFactoryProducer feedbackFactoryProducer;
    private Users user;
    private final UserFactoryProducer userFactoryProducer;
    private StringBuilder commentMessage;
    private java.sql.Timestamp commentDate;

    @Autowired
    public Comment(UserFactoryProducer userFactoryProducer, FeedbackFactoryProducer feedbackFactoryProducer) {
        this.userFactoryProducer = userFactoryProducer;
        this.feedbackFactoryProducer = feedbackFactoryProducer;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public Feedback getFeedback() {
        if (this.user == null){
            throw new RuntimeException("User must be initialized by using setFeedbackInstance() method.");
        }
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void setFeedbackInstance(FeedbackType feedbackType){
        this.feedback = this.feedbackFactoryProducer.getFactory(feedbackType).getFeedback();
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

    public StringBuilder getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(StringBuilder commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getCommentID().compareTo(comment.getCommentID()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentID());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", feedback=" + feedback +
                ", user=" + user +
                ", commentMessage=" + commentMessage +
                ", commentDate=" + commentDate +
                '}';
    }
}
