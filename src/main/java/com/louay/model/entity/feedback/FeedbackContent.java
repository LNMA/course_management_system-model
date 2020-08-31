package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.feedback.constant.FeedbackType;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "feedback_content")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"contentDate"}, allowGetters = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "feedback_type", discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "TINYINT(1)")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public abstract class FeedbackContent implements Comparable<FeedbackContent>, Serializable {
    private static final long serialVersionUID = -7450061396329285787L;
    @Id
    @Column(name = "feedback_id", columnDefinition = "BIGINT(20)", nullable = false)
    private Long feedbackId;

    @JsonIgnore
    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = CourseFeedback.class)
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_course_feedback_id_feedback_file_id", foreignKeyDefinition = "FOREIGN KEY (feedback_id) " +
            "REFERENCES course_feedback (feedback_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private CourseFeedback courseFeedback;

    @Column(name = "content_date", columnDefinition = "TIMESTAMP(0)")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Calendar contentDate;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public CourseFeedback getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CourseFeedback courseFeedback) {
        this.courseFeedback = courseFeedback;
    }

    public Calendar getContentDate() {
        return contentDate;
    }

    public void setContentDate(Calendar contentDate) {
        this.contentDate = contentDate;
    }

    abstract public FeedbackType getFeedbackType();

    @Transient
    @Override
    public int compareTo(FeedbackContent o) {
        return this.feedbackId.compareTo(o.getFeedbackId());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackContent that = (FeedbackContent) o;
        return getFeedbackId().equals(that.getFeedbackId());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackId());
    }

    @Transient
    @Override
    public String toString() {
        return "FeedbackContent{" +
                "feedbackId=" + feedbackId +
                ", courseFeedback=" + courseFeedback.getFeedbackID() +
                ", contentDate=" + contentDate.getTime().toString() +
                '}';
    }
}


