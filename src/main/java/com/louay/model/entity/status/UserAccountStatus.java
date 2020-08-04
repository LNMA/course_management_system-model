package com.louay.model.entity.status;

import com.louay.model.entity.users.Users;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "users_status")
public class UserAccountStatus implements Comparable<UserAccountStatus>, Serializable {
    private static final long serialVersionUID = 5066567685882178929L;
    @Id
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(200)")
    private String userId;

    @MapsId(value = "userId")
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_users_status_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users users;

    @Column(name = "is_online", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean online;

    @Column(name = "is_valid", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean valid;

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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Transient
    @Override
    public int compareTo(UserAccountStatus o) {
        return this.userId.compareTo(o.getUserId());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountStatus that = (UserAccountStatus) o;
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
        return "UserAccountStatus{" +
                "userId='" + userId + '\'' +
                ", users=" + users.getEmail() +
                ", online=" + online +
                ", valid=" + valid +
                '}';
    }
}
