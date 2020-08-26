package com.louay.model.entity.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Admin;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "cookie_login")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"cookieGenerateDate"}, allowGetters = true)
public class CookieLogin implements Serializable, Comparable<CookieLogin>{
    private static final long serialVersionUID = -3908092247779839080L;
    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Admin.class)
    @JoinColumn(name = "email", referencedColumnName = "email", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_email_cookie_login_id", foreignKeyDefinition = "FOREIGN KEY (email) " +
            "REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Admin admin;

    @Column(name = "cookie_secure_number", columnDefinition = "INT(10)", nullable = false)
    private Integer secureCode;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "cookie_create_date", columnDefinition = "TIMESTAMP(0)", nullable = false)
    private Calendar cookieGenerateDate;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Integer getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(Integer secureCode) {
        this.secureCode = secureCode;
    }

    public Calendar getCookieGenerateDate() {
        return cookieGenerateDate;
    }

    public void setCookieGenerateDate(Calendar cookieGenerateDate) {
        this.cookieGenerateDate = cookieGenerateDate;
    }

    @Override
    public int compareTo(CookieLogin o) {
        return this.admin.compareTo(o.getAdmin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieLogin that = (CookieLogin) o;
        return getAdmin().equals(that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdmin());
    }

    @Override
    public String toString() {
        return "CookieLogin{" +
                "admin=" + admin.getEmail() +
                ", secureCode=" + secureCode +
                ", cookieGenerateDate=" + cookieGenerateDate +
                '}';
    }
}
