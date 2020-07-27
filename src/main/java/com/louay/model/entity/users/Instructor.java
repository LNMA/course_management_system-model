package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.InstructorProfileVisibility;
import com.louay.model.entity.users.constant.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("instructor")
@Entity
@Table(schema = "course_management_system", name = "instructors_details")
public class Instructor extends Users {
    private static final long serialVersionUID = 1227822599613464185L;
    @Column(name = "instructors_id")
    private String instructorsId;

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

    @Override
    public String getEmail() {
        return this.instructorsId;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        this.instructorsId = email;
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

    public InstructorProfileVisibility getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(InstructorProfileVisibility profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    @Override
    public Role getUserRole() {
        return Role.INSTRUCTOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor)) return false;
        if (!super.equals(o)) return false;
        Instructor that = (Instructor) o;
        return instructorsId.equals(that.instructorsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), instructorsId);
    }

    @Override
    public String toString() {
        return super.toString()+", Instructor{" +
                "instructorsId='" + instructorsId + '\'' +
                ", headline='" + headline + '\'' +
                ", specialty='" + specialty + '\'' +
                ", nickname='" + nickname + '\'' +
                ", portfolio='" + portfolio + '\'' +
                ", profileVisibility=" + profileVisibility +
                '}';
    }
}
