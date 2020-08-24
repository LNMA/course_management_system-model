package com.louay.model.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.constant.Gender;
import com.louay.model.entity.users.constant.Role;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Polymorphism(type = PolymorphismType.EXPLICIT)
@AttributeOverride(name = "email", column = @Column(name = "user_id", columnDefinition = "VARCHAR(200)", nullable = false))
@Table( name = "users_details")
public class Users extends Accounts {
    private static final long serialVersionUID = 4919354445622068411L;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Admin.class)
    @JoinColumn(name = "user_id", referencedColumnName = "email", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_email_users_details_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Admin admin;

    @Column(name = "forename", length = 50, nullable = false, columnDefinition = "VARCHAR(50)")
    private String forename;

    @Column(name = "surname", length = 50, nullable = false,columnDefinition = "VARCHAR(50)")
    private String surname;

    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE')")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone", length = 20, columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(name = "birthday",columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Calendar birthday;

    @Column(name = "country", length = 60, columnDefinition = "VARCHAR(60)")
    private String country;

    @Column(name = "state", length = 60, columnDefinition = "VARCHAR(60)")
    private String state;

    @Column(name = "address", length = 200, columnDefinition = "VARCHAR(200)")
    private String address;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
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

    @Transient
    @Override
    public Role getUserRole() {
        return Role.USER;
    }

    @Transient
    public String getAge(){
        TimeZone timeZone = this.birthday.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        LocalDate birthday = LocalDate.ofInstant(this.birthday.toInstant(), zoneId);
        LocalDate now = LocalDate.now();
        int year = birthday.until(now).getYears();
        int month = birthday.until(now).getMonths();
        int day = birthday.until(now).getDays();
        return String.format("%d day, %d month, %d year",day, month, year);
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", Users{" +
                "admin=" + admin.getEmail() +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", phone=" + phone +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
