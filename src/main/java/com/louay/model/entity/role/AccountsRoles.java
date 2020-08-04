package com.louay.model.entity.role;

import com.louay.model.entity.users.constant.Role;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "roles")
public class AccountsRoles implements Comparable<AccountsRoles>, Serializable {
    private static final long serialVersionUID = -2323257288892496910L;
    @Id
    @Column(name = "role_id", nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleID;

    @Column(name = "role_name", nullable = false, columnDefinition = "ENUM('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @Enumerated(EnumType.STRING)
    private Role roleName;

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    @Transient
    @Override
    public int compareTo(AccountsRoles o) {
        return this.roleID.compareTo(o.getRoleID());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsRoles that = (AccountsRoles) o;
        return getRoleID().equals(that.getRoleID());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getRoleID());
    }

    @Transient
    @Override
    public String toString() {
        return "AccountsRoles{" +
                "roleID=" + roleID +
                ", roleName=" + roleName +
                '}';
    }
}
