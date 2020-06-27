package com.louay.model.chains.users;

import com.louay.model.chains.users.constant.UserRole;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public abstract class Accounts implements Comparable<Accounts> {
    private String email;
    private String forename;
    private String surname;
    private String password;
    private java.sql.Timestamp joinDate;
    private byte[] picture;
    private java.sql.Timestamp uploadPicDate;

    public Accounts() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Timestamp getUploadPicDate() {
        return uploadPicDate;
    }

    public void setUploadPicDate(Timestamp uploadPicDate) {
        this.uploadPicDate = uploadPicDate;
    }

    public StringBuilder getBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.picture));

        return stringBase46;
    }

    @Override
    public int compareTo(Accounts o) {
        return this.getEmail().compareTo(o.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return getEmail().equals(accounts.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    abstract public UserRole getUserRole();

    @Override
    public String toString() {
        return "Accounts{" +
                "email='" + email + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                ", picture=" + Arrays.toString(picture) +
                ", uploadPicDate=" + uploadPicDate +
                '}';
    }
}
