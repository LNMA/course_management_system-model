package com.louay.model.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.constant.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"joinDate"}, allowGetters = true)
@AttributeOverride(name = "email", column = @Column(name = "email", columnDefinition = "VARCHAR(200)", nullable = false))
public class Admin extends Accounts {
    private static final long serialVersionUID = 8921901148786175549L;
    @Column(name = "password", nullable = false, length = 100, columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(name = "join_date", nullable = false, columnDefinition = "TIMESTAMP(0)")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar joinDate;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Calendar joinDate) {
        this.joinDate = joinDate;
    }

    @Transient
    @Override
    public Role getUserRole() {
        return Role.ADMIN;
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", Admin{" +
                "password='" + password + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
