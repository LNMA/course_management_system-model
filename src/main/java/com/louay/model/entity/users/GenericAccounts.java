package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.Role;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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

    abstract public Role getUserRole();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericAccounts genericAccounts = (GenericAccounts) o;
        return getEmail().equals(genericAccounts.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return "GenericAccounts{" +
                "email='" + email + '\'' +
                '}';
    }
}
