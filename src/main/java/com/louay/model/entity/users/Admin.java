package com.louay.model.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.constant.Role;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(schema = "course_management_system", name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"joinDate"}, allowGetters = true)
@AttributeOverride(name = "email", column = @Column(name = "email"))
public class Admin extends GenericAccounts {
    private static final long serialVersionUID = -3123669786464896772L;
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "join_date", nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public Role getUserRole() {
        return Role.ADMIN;
    }

    @Override
    public int compareTo(GenericAccounts o) {
        return this.getEmail().compareTo(o.getEmail());
    }

    @Override
    public String toString() {
        return super.toString()+", Admin{" +
                "password='" + password + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
