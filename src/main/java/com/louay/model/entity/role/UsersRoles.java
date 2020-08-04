package com.louay.model.entity.role;

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
@Table(name = "users_roles", indexes = {
        @Index(name = "users_roles_role_id_UNIQUE", columnList = "role_id", unique = true),
        @Index(name = "users_roles_user_id_UNIQUE", columnList = "user_id", unique = true)})
public class UsersRoles implements Comparable<UsersRoles>, Serializable {
    private static final long serialVersionUID = 6558013226551832736L;
    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Admin.class)
    @JoinColumn(name = "user_id", referencedColumnName = "email", nullable = false, unique = true, foreignKey =
    @ForeignKey(name = "fk_users_email_users_roles_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE"), columnDefinition = "VARCHAR(200)")
    private Admin users;

    @Id
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, targetEntity = AccountsRoles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, unique = true, foreignKey =
    @ForeignKey(name = "fk_roles_role_id_users_roles_role_id", foreignKeyDefinition = "FOREIGN KEY (role_id) " +
            "REFERENCES roles (role_id) ON DELETE CASCADE ON UPDATE CASCADE"), columnDefinition = "BIGINT(20)")
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
