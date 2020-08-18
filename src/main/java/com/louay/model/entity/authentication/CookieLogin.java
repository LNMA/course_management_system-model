package com.louay.model.entity.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Admin;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "cookie_login")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"cookieGenerateDate"}, allowGetters = true)
public class CookieLogin implements Serializable, Comparable<CookieLogin>{
    private static final long serialVersionUID = -2375073478099428433L;
    @Id
    @Column(name = "email", length = 200, columnDefinition = "VARCHAR(200)", nullable = false)
    private String email;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Admin.class)
    @JoinColumn(name = "email", referencedColumnName = "email", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_email_cookie_login_id", foreignKeyDefinition = "FOREIGN KEY (email) " +
            "REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Admin admin;

    @Column(name = "cookie_secure_number", columnDefinition = "INT(10)")
    private Integer secureCode;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "cookie_create_date", columnDefinition = "TIMESTAMP(0)")
    private Date cookieGenerateDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Date getCookieGenerateDate() {
        return cookieGenerateDate;
    }

    public void setCookieGenerateDate(Date cookieGenerateDate) {
        this.cookieGenerateDate = cookieGenerateDate;
    }

    @Override
    public int compareTo(CookieLogin o) {
        return this.email.compareTo(o.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieLogin that = (CookieLogin) o;
        return getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return "CookieLogin{" +
                "email='" + email + '\'' +
                ", admin=" + admin.getEmail() +
                ", secureCode=" + secureCode +
                ", cookieGenerateDate=" + cookieGenerateDate +
                '}';
    }
}
