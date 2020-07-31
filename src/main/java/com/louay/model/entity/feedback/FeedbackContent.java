package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feedback_content")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"contentDate"}, allowGetters = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "feedback_type", discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "TINYINT(1)")
@Polymorphism(type = PolymorphismType.EXPLICIT)
@PrimaryKeyJoinColumn(name = "feedback_id", referencedColumnName = "feedback_id")
public abstract class FeedbackContent extends CourseFeedback {
    private static final long serialVersionUID = 8337819359134959890L;
    @Column(name = "content_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date contentDate;

    public Date getContentDate() {
        return contentDate;
    }

    public void setContentDate(Date contentDate) {
        this.contentDate = contentDate;
    }

    @Override
    public String toString() {
        return "FeedbackContent{" +
                "contentDate=" + contentDate +
                '}';
    }
}
