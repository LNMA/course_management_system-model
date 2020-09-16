package com.louay.model.entity.notification;

import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.notification.constant.NotificationType;
import com.louay.model.entity.users.Users;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "notifications", indexes = {
        @Index(name = "notifications_user_id_IX", columnList = "user_id"),
        @Index(name = "notifications_course_id_IX", columnList = "course_id"),
        @Index(name = "notifications_material_id_IX", columnList = "material_id"),
        @Index(name = "notifications_feedback_id_IX", columnList = "material_id")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kind", columnDefinition = "TINYINT(1)", discriminatorType = DiscriminatorType.INTEGER)
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class UserNotification implements Serializable, Comparable<UserNotification> {
    private static final long serialVersionUID = 978559942020312649L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false, columnDefinition = "BIGINT(20)")
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_notifications_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Courses.class)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_courses_course_id_notifications_course_id", foreignKeyDefinition = "FOREIGN KEY " +
            "(course_id) REFERENCES courses (course_id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Courses course;

    @Column(name = "notification_type", columnDefinition = "ENUM('MATERIAL', 'FEEDBACK')")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "is_seen", columnDefinition = "TINYINT(1)")
    private Boolean isSeen;

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    @Transient
    @Override
    public int compareTo(UserNotification o) {
        return this.notificationId.compareTo(o.getNotificationId());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNotification that = (UserNotification) o;
        return getNotificationId().equals(that.getNotificationId());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getNotificationId());
    }

    @Transient
    @Override
    public String toString() {
        return "UserNotification{" +
                "notificationId=" + notificationId +
                ", users=" + users.getEmail() +
                ", course=" + course.getCourseID() +
                ", notificationType=" + notificationType +
                ", isSeen=" + isSeen +
                '}';
    }
}
