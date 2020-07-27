package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.Role;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("users")
@Entity
@Table(schema = "course_management_system", name = "users_details")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Polymorphism(type = PolymorphismType.EXPLICIT)
@AttributeOverride(name = "email", column = @Column(name = "user_id"))
public class Users extends GenericAccounts {
    private static final long serialVersionUID = 5559907892093012373L;
    @Column(name = "forename", nullable = false, length = 50)
    private String forename;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "address", length = 200)
    private String address;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Role getUserRole() {
        return Role.USER;
    }

    @Override
    public int compareTo(GenericAccounts o) {
        return this.getEmail().compareTo(o.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        if (!super.equals(o)) return false;
        Users users = (Users) o;
        return getForename().equals(users.getForename()) &&
                getSurname().equals(users.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getForename(), getSurname(), getGender(), getBirthday(), getCountry());
    }

    @Override
    public String toString() {
        return super.toString() + ", Users{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
