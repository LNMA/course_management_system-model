package com.louay.model.chains.users;

import com.louay.model.chains.users.constant.UserRole;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class Instructor extends Users{
    private String headline;
    private String specialty;
    private String nickname;
    private String portfolio;
    private String profileVisibility;

    public Instructor() {
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(String profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor)) return false;
        if (!super.equals(o)) return false;
        Instructor that = (Instructor) o;
        return getHeadline().equals(that.getHeadline()) &&
                getSpecialty().equals(that.getSpecialty()) &&
                getNickname().equals(that.getNickname()) &&
                getPortfolio().equals(that.getPortfolio()) &&
                getProfileVisibility().equals(that.getProfileVisibility());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHeadline(), getSpecialty(), getNickname(), getPortfolio(), getProfileVisibility());
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.INSTRUCTOR;
    }

    @Override
    public String toString() {
        return super.toString() + "Instructor{" +
                "headline='" + headline + '\'' +
                ", specialty='" + specialty + '\'' +
                ", nickname='" + nickname + '\'' +
                ", portfolio='" + portfolio + '\'' +
                ", profileVisibility='" + profileVisibility + '\'' +
                '}';
    }
}
