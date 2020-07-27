package com.louay.model.entity.users.picute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Admin;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(schema = "course_management_system", name = "users_profile_picture")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"uploadPicDate"}, allowGetters = true)
public class AccountPicture implements Comparable<AccountPicture>, Serializable {
    private static final long serialVersionUID = -6081540563892991421L;
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private Admin admin;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date uploadPicDate;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Date getUploadPicDate() {
        return uploadPicDate;
    }

    public void setUploadPicDate(Date uploadPicDate) {
        this.uploadPicDate = uploadPicDate;
    }

    public StringBuilder getBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.picture));

        return stringBase46;
    }

    @Override
    public int compareTo(AccountPicture o) {
        return this.admin.compareTo(o.getAdmin());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPicture that = (AccountPicture) o;
        return getAdmin().equals(that.getAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdmin());
    }

    @Override
    public String toString() {
        return "AccountPicture{" +
                "accounts=" + admin +
                ", picture=" + Arrays.toString(picture) +
                ", uploadPicDate=" + uploadPicDate +
                '}';
    }
}
