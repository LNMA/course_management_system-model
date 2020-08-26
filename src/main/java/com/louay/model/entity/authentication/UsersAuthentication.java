package com.louay.model.entity.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Users;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "users_authentication")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"lastUpdateDate"}, allowGetters = true)
public class UsersAuthentication implements Serializable, Comparable<UsersAuthentication> {
    private static final long serialVersionUID = -1977165795674363512L;
    @Id
    @Column(name = "user_id", length = 200, columnDefinition = "VARCHAR(200)", nullable = false)
    private String userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_user_details_id_users_authentication_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users users;

    @Column(name = "verification_number", columnDefinition = "INT(10)")
    private Integer verificationNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "last_update_date", columnDefinition = "TIMESTAMP(0)")
    private Calendar lastUpdateDate;

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

    public Integer getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(Integer verificationNumber) {
        this.verificationNumber = verificationNumber;
    }

    public Calendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Calendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public int compareTo(UsersAuthentication o) {
        return this.userId.compareTo(o.getUserId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAuthentication that = (UsersAuthentication) o;
        return getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return "UsersAuthentication{" +
                "userId='" + userId + '\'' +
                ", users=" + users.getEmail() +
                ", verificationNumber=" + verificationNumber +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
