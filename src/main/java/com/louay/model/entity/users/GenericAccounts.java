package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.Role;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Polymorphism(type = PolymorphismType.EXPLICIT)
public abstract class GenericAccounts implements Comparable<GenericAccounts>, Serializable {
    private static final long serialVersionUID = -4138899792068390729L;
    @Id
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    abstract public Role getUserRole();

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericAccounts genericAccounts = (GenericAccounts) o;
        return getEmail().equals(genericAccounts.getEmail());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Transient
    @Override
    public String toString() {
        return "GenericAccounts{" +
                "email='" + email + '\'' +
                '}';
    }
}
