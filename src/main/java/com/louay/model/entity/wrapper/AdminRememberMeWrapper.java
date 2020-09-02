package com.louay.model.entity.wrapper;

import com.louay.model.entity.users.Admin;

import java.io.Serializable;
import java.util.Objects;

public class AdminRememberMeWrapper implements Serializable, Comparable<AdminRememberMeWrapper> {
    private static final long serialVersionUID = 7812558456080663786L;
    private Admin admin;
    private boolean rememberMe;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public int compareTo(AdminRememberMeWrapper o) {
        return this.admin.compareTo(o.getAdmin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminRememberMeWrapper that = (AdminRememberMeWrapper) o;
        return getAdmin().equals(that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdmin());
    }

    @Override
    public String toString() {
        return "AdminRememberMeWrapper{" +
                "admin=" + admin +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
