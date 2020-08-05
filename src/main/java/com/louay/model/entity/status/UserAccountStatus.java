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
    private static final long serialVersionUID = 6669036328550767638L;
    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_users_status_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users users;

    @Column(name = "is_online", nullable = false, columnDefinition = "TINYINT(1) default 0")
    private Boolean online;

    @Column(name = "is_valid", nullable = false, columnDefinition = "TINYINT(1) default 0")
    private Boolean valid;

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
        return this.users.compareTo(o.getUsers());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountStatus that = (UserAccountStatus) o;
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
        return "UserAccountStatus{" +
                "users=" + users.getEmail() +
                ", online=" + online +
                ", valid=" + valid +
                '}';
    }
}
