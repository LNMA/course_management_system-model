package com.louay.model.entity.users;

import com.louay.model.entity.users.constant.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("student")
@Entity
@PrimaryKeyJoinColumn(name = "student_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)",
        foreignKey = @ForeignKey(name = "fk_users_details_id_students_details_student_id", foreignKeyDefinition =
        "FOREIGN KEY (student_id) REFERENCES users_details (user_id) ON UPDATE DELETE ON UPDATE CASCADE"))
@Table(name = "students_details")
public class Student extends Users {
    private static final long serialVersionUID = 6321379944607000788L;
    @Column(name = "headline", length = 300, columnDefinition = "VARCHAR(300)")
    private String headline;

    @Column(name = "interests", length = 300, columnDefinition = "VARCHAR(300)")
    private String interests;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Transient
    @Override
    public Role getUserRole() {
        return Role.STUDENT;
    }

    @Transient
    @Override
    public int compareTo(GenericAccounts o) {
        return super.getEmail().compareTo(o.getEmail());
    }

    @Transient
    @Override
    public String toString() {
        return super.toString()+", Student{" +
                "headline='" + headline + '\'' +
                ", interests='" + interests + '\'' +
                '}';
    }
}
