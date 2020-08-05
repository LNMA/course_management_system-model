package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.Role;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Polymorphism(type = PolymorphismType.EXPLICIT)
public abstract class Accounts implements Comparable<Accounts>, Serializable {
    private static final long serialVersionUID = 5064878654696864127L;
    @Id
    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    abstract public Role getUserRole();

    @Override
    public int compareTo(Accounts o) {
        return this.email.compareTo(o.getEmail());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return getEmail().equals(accounts.getEmail());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Transient
    @Override
    public String toString() {
        return "Accounts{" +
                "email='" + email + '\'' +
                '}';
    }
}
