package com.louay.model.entity.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Users;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"signInDate"}, allowGetters = true)
@Table(name = "users_sign_in_date", indexes = {@Index(name = "users_sign_in_date_user_id_IX", columnList = "user_id")})
public class UserSignIn implements Comparable<UserSignIn>, Serializable {
    private static final long serialVersionUID = 392910671765313707L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_sign_in_id", nullable = false, columnDefinition = "BIGINT(20)")
    private Long userSignInId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_users_sign_in_date_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users users;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "sign_in_date", nullable = false, columnDefinition = "TIMESTAMP(0)")
    private Date signInDate;

    public Long getUserSignInId() {
        return userSignInId;
    }

    public void setUserSignInId(Long userSignInId) {
        this.userSignInId = userSignInId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }

    @Override
    public int compareTo(UserSignIn o) {
        return this.userSignInId.compareTo(o.getUserSignInId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignIn that = (UserSignIn) o;
        return getUserSignInId().equals(that.getUserSignInId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserSignInId());
    }

    @Override
    public String toString() {
        return "UserSignIn{" +
                "userSignInId=" + userSignInId +
                ", users=" + users.getEmail() +
                ", signInDate=" + signInDate +
                '}';
    }
}
