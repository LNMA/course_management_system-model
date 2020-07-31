package com.louay.model.entity.users.role;

import com.louay.model.entity.users.Admin;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(schema = "course_management_system", name = "users_roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
public class UsersRoles implements Comparable<UsersRoles>, Serializable {
    private static final long serialVersionUID = -1414850559717508755L;
    @Id
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Admin.class)
    @JoinColumn(name = "user_id", referencedColumnName = "email", nullable = false, unique = true)
    private Admin users;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, targetEntity = AccountsRoles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, unique = true)
    private AccountsRoles accountsRoles;

    public Admin getUsers() {
        return users;
    }

    public void setUsers(Admin users) {
        this.users = users;
    }

    public AccountsRoles getAccountsRoles() {
        return accountsRoles;
    }

    public void setAccountsRoles(AccountsRoles accountsRoles) {
        this.accountsRoles = accountsRoles;
    }


    @Transient
    @Override
    public int compareTo(UsersRoles o) {
        return this.users.compareTo(o.getUsers());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRoles that = (UsersRoles) o;
        return getUsers().equals(that.getUsers()) &&
                getAccountsRoles().equals(that.getAccountsRoles());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getUsers(), getAccountsRoles());
    }

    @Transient
    @Override
    public String toString() {
        return "UsersRoles{" +
                "users=" + users +
                ", accountsRoles=" + accountsRoles +
                '}';
    }
}
