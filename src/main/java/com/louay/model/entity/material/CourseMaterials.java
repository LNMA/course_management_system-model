package com.louay.model.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.courses.Courses;
import com.louay.model.entity.users.Users;
import com.louay.model.util.constant.ClassName;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "courses_materials", indexes = {
        @Index(name = "courses_materials_course_id_IX", columnList = "course_id"),
        @Index(name = "courses_materials_user_id_IX", columnList = "user_id")})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"materialDate"}, allowGetters = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class CourseMaterials implements Comparable<CourseMaterials>, Serializable {
    private static final long serialVersionUID = -3651014107458022606L;
    @Id
    @Column(name = "material_id", unique = true, nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialID;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_courses_course_id_courses_materials_course_id", foreignKeyDefinition = "FOREIGN KEY " +
            "(course_id) REFERENCES courses (course_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Courses course;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(200)", foreignKey =
    @ForeignKey(name = "fk_users_details_id_courses_materials_user_id", foreignKeyDefinition = "FOREIGN KEY (user_id)" +
            " REFERENCES users_details (user_id) ON DELETE CASCADE ON UPDATE CASCADE"), nullable = false)
    private Users user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "material_date", nullable = false, columnDefinition = "TIMESTAMP(0)")
    @CreatedDate
    private Calendar materialDate;

    public Long getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Long materialID) {
        this.materialID = materialID;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Calendar getMaterialDate() {
        return materialDate;
    }

    public void setMaterialDate(Calendar materialDate) {
        this.materialDate = materialDate;
    }

    public String getMaterialDateString() {
        return this.materialDate.getTime().toString();
    }

    public ClassName getClassName() {
        return ClassName.COURSE_MATERIAL;
    }

    @Transient
    @Override
    public int compareTo(CourseMaterials o) {
        return this.materialID.compareTo(o.materialID);
    }

    @Transient
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseMaterials courseMaterials = (CourseMaterials) o;
        return getMaterialID().equals(courseMaterials.getMaterialID());
    }

    @Transient
    @Override
    public int hashCode() {
        return Objects.hash(getMaterialID());
    }

    @Transient
    @Override
    public String toString() {
        return "CourseMaterials{" +
                "materialID=" + materialID +
                ", course=" + course.getCourseID() +
                ", user=" + user.getEmail() +
                ", materialDate=" + materialDate +
                '}';
    }
}
