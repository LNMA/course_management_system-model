package com.louay.model.chains.users;

import java.util.Objects;

public class AccountsRoles {
    private Double roleID;
    private String roleName;
    private Accounts accounts;

    public AccountsRoles() {
    }

    public Double getRoleID() {
        return roleID;
    }

    public void setRoleID(Double roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsRoles that = (AccountsRoles) o;
        return getRoleID().compareTo(that.getRoleID()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleID());
    }

    @Override
    public String toString() {
        return "UsersRoles{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
