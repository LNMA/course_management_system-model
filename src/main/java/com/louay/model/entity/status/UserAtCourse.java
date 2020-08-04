package com.louay.model.entity.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "users_course_join")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"joinDate"}, allowGetters = true)
public class UserAtCourse implements Comparable<UserAtCourse>, Serializable {
    private static final long serialVersionUID = 2844728069617447889L;
    @Id
    @Column(name = "users_id", columnDefinition = "VARCHAR(200)", nullable = false)
    private String userId;

    @MapsId("userId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_users_course_join_user_id", foreignKeyDefinition = "FOREIGN KEY (users_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users users;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "join_date", columnDefinition = "TIMESTAMP(0)")
    private Date joinDate;

    @Column(name = "is_busy", columnDefinition = "TINYINT(1) default 0")
    private Boolean busy;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    @Transient
    @Override
    public int compareTo(UserAtCourse o) {
        return this.userId.compareTo(o.getUserId());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAtCourse that = (UserAtCourse) o;
        return getUserId().equals(that.getUserId());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Transient
    @Override
    public String toString() {
        return "UserAtCourse{" +
                "userId='" + userId + '\'' +
                ", users=" + users.getEmail() +
                ", joinDate=" + joinDate +
                ", busy=" + busy +
                '}';
    }
}
