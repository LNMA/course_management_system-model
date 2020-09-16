package com.louay.model.entity.notification;

import com.louay.model.entity.material.CourseMaterials;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class MaterialNotification extends UserNotification {
    private static final long serialVersionUID = 2330867902212015611L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id", columnDefinition = "BIGINT(20)", foreignKey =
    @ForeignKey(name = "fk_course_material_id_notifications_material_id", foreignKeyDefinition =
            "FOREIGN KEY (material_id) REFERENCES courses_materials (material_id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private CourseMaterials courseMaterials;

    public CourseMaterials getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(CourseMaterials courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", MaterialNotification{" +
                "courseMaterials=" + courseMaterials.getMaterialID() +
                '}';
    }
}
