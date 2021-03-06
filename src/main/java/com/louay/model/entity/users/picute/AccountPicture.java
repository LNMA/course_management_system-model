package com.louay.model.entity.users.picute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.users.Users;
import org.hibernate.annotations.LazyGroup;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users_profile_picture")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"uploadPicDate"}, allowGetters = true)
public class AccountPicture implements Comparable<AccountPicture>, Serializable {
    private static final long serialVersionUID = 2010226808274376094L;
    @Id
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_profile_picture_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id) " +
            "REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users users;

    @JsonIgnore
    @Lob
    @Column(name = "picture", columnDefinition = "LONGBLOB", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    @LazyGroup("lobs")
    private byte[] picture;

    @Column(name = "upload_date", columnDefinition = "TIMESTAMP(0)", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Calendar uploadPicDate;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getUsersEmail() {
        return users.getEmail();
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Calendar getUploadPicDate() {
        return uploadPicDate;
    }

    public String getUploadPicDateString() {
        return uploadPicDate.getTime().toString();
    }

    public void setUploadPicDate(Calendar uploadPicDate) {
        this.uploadPicDate = uploadPicDate;
    }

    public StringBuilder getProfilePictureBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.picture));

        return stringBase46;
    }

    @Transient
    @Override
    public int compareTo(AccountPicture o) {
        return this.users.compareTo(o.getUsers());
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPicture that = (AccountPicture) o;
        return getUsers().equals(that.getUsers());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getUsers());
    }

    @Transient
    @Override
    public String toString() {
        return "AccountPicture{" +
                "users=" + users.getEmail() +
                ", picture=" + Arrays.toString(picture) +
                ", uploadPicDate=" + uploadPicDate.getTime().toString() +
                '}';
    }
}
