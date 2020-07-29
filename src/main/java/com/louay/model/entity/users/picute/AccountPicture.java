package com.louay.model.entity.users.picute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.Users;
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
    private static final long serialVersionUID = -1629166353873013035L;
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users users;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date uploadPicDate;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        return this.users.compareTo(o.getUsers());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPicture that = (AccountPicture) o;
        return getUsers().equals(that.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsers());
    }

    @Override
    public String toString() {
        return "AccountPicture{" +
                "users=" + users +
                ", picture=" + Arrays.toString(picture) +
                ", uploadPicDate=" + uploadPicDate +
                '}';
    }
}
