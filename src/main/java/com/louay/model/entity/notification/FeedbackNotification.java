package com.louay.model.entity.notification;

import com.louay.model.entity.feedback.CourseFeedback;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class FeedbackNotification extends UserNotification {
    private static final long serialVersionUID = -1421730724414304839L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_courses_feedback_id_notifications_feedback_id", foreignKeyDefinition =
            "FOREIGN KEY (feedback_id) REFERENCES course_feedback (feedback_id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private CourseFeedback courseFeedback;

    public CourseFeedback getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CourseFeedback courseFeedback) {
        this.courseFeedback = courseFeedback;
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", FeedbackNotification{" +
                "courseFeedback=" + courseFeedback.getFeedbackID() +
                '}';
    }
}
