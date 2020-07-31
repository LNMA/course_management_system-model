package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.entity.users.constant.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("instructor")
@Entity
@PrimaryKeyJoinColumn(name = "instructors_id", referencedColumnName = "user_id")
@Table(name = "instructors_details")
public class Instructor extends Users {
    private static final long serialVersionUID = 8191124843845259614L;
    @Column(name = "headline", length = 300)
    private String headline;

    @Column(name = "specialty", length = 300)
    private String specialty;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "portfolio", length = 300)
    private String portfolio;

    @Column(name = "profile_visibility")
    @Enumerated(EnumType.STRING)
    private InstructorProfileVisibility profileVisibility;

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

    public InstructorProfileVisibility getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(InstructorProfileVisibility profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    @Transient
    @Override
    public Role getUserRole() {
        return Role.INSTRUCTOR;
    }

    @Transient
    @Override
    public int compareTo(GenericAccounts o) {
        return super.getEmail().compareTo(o.getEmail());
    }

    @Transient
    @Override
    public String toString() {
        return super.toString()+", Instructor{" +
                "headline='" + headline + '\'' +
                ", specialty='" + specialty + '\'' +
                ", nickname='" + nickname + '\'' +
                ", portfolio='" + portfolio + '\'' +
                ", profileVisibility=" + profileVisibility +
                '}';
    }
}
