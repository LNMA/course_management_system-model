package com.louay.model.entity.authentication;

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
@Table(name = "users_authentication")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"lastUpdateDate"}, allowGetters = true)
public class UsersAuthentication implements Serializable, Comparable<UsersAuthentication> {
    private static final long serialVersionUID = 9176633749531513785L;
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
    private Date lastUpdateDate;

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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
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
                ", users=" + users +
                ", verificationNumber=" + verificationNumber +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
