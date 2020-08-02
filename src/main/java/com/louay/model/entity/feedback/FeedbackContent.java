package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.feedback.constant.FeedbackType;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "feedback_content")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"contentDate"}, allowGetters = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "feedback_type", discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "TINYINT(1)")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public abstract class FeedbackContent implements Comparable<FeedbackContent>, Serializable {
    private static final long serialVersionUID = 9167299451535423240L;
    @Id
    @Column(name = "feedback_id")
    private Long feedbackId;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id")
    private CourseFeedback courseFeedback;

    @Column(name = "content_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date contentDate;



    public CourseFeedback getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CourseFeedback courseFeedback) {
        this.courseFeedback = courseFeedback;
    }

    public Date getContentDate() {
        return contentDate;
    }

    public void setContentDate(Date contentDate) {
        this.contentDate = contentDate;
    }

    @Transient
    abstract public FeedbackType getFeedbackType();

    @Transient
    @Override
    public int compareTo(FeedbackContent o) {
        return this.courseFeedback.compareTo(o.getCourseFeedback());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackContent that = (FeedbackContent) o;
        return getCourseFeedback().equals(that.getCourseFeedback());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getCourseFeedback());
    }

    @Transient
    @Override
    public String toString() {
        return "FeedbackContent{" +
                "courseFeedback=" + courseFeedback.getFeedbackID() +
                ", contentDate=" + contentDate +
                '}';
    }
}


