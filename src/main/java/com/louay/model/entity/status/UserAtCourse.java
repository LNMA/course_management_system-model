package com.louay.model.entity.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Users;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "users_course_join")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"joinDate"}, allowGetters = true)
public class UserAtCourse implements Comparable<UserAtCourse>, Serializable {
    private static final long serialVersionUID = -4296226793260819196L;
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_users_course_join_user_id", foreignKeyDefinition = "FOREIGN KEY (users_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users users;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "join_date", columnDefinition = "TIMESTAMP(0)")
    private Calendar joinDate;

    @Column(name = "is_busy", columnDefinition = "TINYINT(1) default 0")
    private Boolean busy;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Calendar getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Calendar joinDate) {
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
        return this.users.compareTo(o.getUsers());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAtCourse that = (UserAtCourse) o;
        return getUsers().equals(that.getUsers());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getUsers());
    }

    @Transient
    @Override
    public String toString() {
        return "UserAtCourse{" +
                "users=" + users.getEmail() +
                ", joinDate=" + joinDate +
                ", busy=" + busy +
                '}';
    }
}
