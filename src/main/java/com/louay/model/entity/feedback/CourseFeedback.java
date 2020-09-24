package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.feedback.comment.Comment;
import com.louay.model.entity.feedback.constant.FeedbackType;
import com.louay.model.entity.users.Users;
import com.louay.model.util.constant.ClassName;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "course_feedback", indexes = {
        @Index(name = "course_file_feedback_course_id_IX", columnList = "course_id"),
        @Index(name = "course_feedback_user_id_IX", columnList = "user_id")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"feedbackDate"}, allowGetters = true)
public class CourseFeedback implements Serializable, Comparable<CourseFeedback> {
    private static final long serialVersionUID = -3814856173811544411L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false, columnDefinition = "BIGINT(20)")
    private Long feedbackID;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Courses.class)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_courses_course_id_course_file_feedback_course_id", foreignKeyDefinition = "FOREIGN KEY " +
            "(course_id) REFERENCES courses (course_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Courses course;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_course_feedback_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users user;

    @Column(name = "feedback_date", columnDefinition = "TIMESTAMP(0)", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Calendar feedbackDate;

    @Column(name = "feedback_content", columnDefinition = "ENUM('MESSAGE', 'FILE', 'ALL')", nullable = false)
    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "courseFeedback", cascade = CascadeType.ALL, orphanRemoval = true)
    private FeedbackContent feedbackContent;

    @JsonIgnore
    @OneToMany(mappedBy = "courseFeedback", fetch = FetchType.LAZY)
    private Set<Comment> comment = new HashSet<>();

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

    public Calendar getFeedbackDate() {
        return feedbackDate;
    }

    public String getFeedbackDateString() {
        return this.feedbackDate.getTime().toString();
    }

    public void setFeedbackDate(Calendar feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public FeedbackContent getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(FeedbackContent feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public TreeSet<Comment> getCommentTreeSet() {
        TreeSet<Comment> commentTreeSet = new TreeSet<>();
        if (!this.comment.isEmpty()) {
            commentTreeSet.addAll(this.comment);
        }
        return commentTreeSet;
    }

    public ClassName getClassName() {
        return ClassName.COURSE_FEEDBACK;
    }

    @Transient
    @Override
    public int compareTo(CourseFeedback o) {
        if (this.feedbackDate == null || o.getFeedbackDate() == null) {
            return 0;
        }
        if (this.feedbackDate.after(o.getFeedbackDate())) {
            return -1;
        }
        if (this.feedbackDate.before(o.getFeedbackDate())) {
            return 1;
        }
        return 0;
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseFeedback that = (CourseFeedback) o;
        return this.feedbackID.equals(that.getFeedbackID());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(this.feedbackID);
    }

    @Transient
    @Override
    public String toString() {
        return "CourseFeedback{" +
                "feedbackID=" + feedbackID +
                ", course=" + course.getCourseID() +
                ", user=" + user.getEmail() +
                ", feedbackDate=" + feedbackDate.getTime().toString() +
                ", feedbackType=" + feedbackType +
                '}';
    }
}


