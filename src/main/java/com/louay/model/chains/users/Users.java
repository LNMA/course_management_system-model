package com.louay.model.chains.users;

import java.sql.Date;
import java.util.Objects;

public abstract class Users extends Accounts {
    private String gender;
    private Integer phone;
    private java.sql.Date birthday;
    private String country;
    private String state;
    private String address;

    public Users() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        if (!super.equals(o)) return false;
        Users users = (Users) o;
        return Objects.equals(getGender(), users.getGender()) &&
                Objects.equals(getPhone(), users.getPhone()) &&
                Objects.equals(getBirthday(), users.getBirthday()) &&
                Objects.equals(getCountry(), users.getCountry()) &&
                Objects.equals(getState(), users.getState()) &&
                Objects.equals(getAddress(), users.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGender(), getPhone(), getBirthday(), getCountry(), getState(), getAddress());
    }

    @Override
    public String toString() {
        return super.toString() + ", Users{" +
                "gender='" + gender + '\'' +
                ", phone=" + phone +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
