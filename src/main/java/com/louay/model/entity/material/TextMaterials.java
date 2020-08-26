package com.louay.model.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.material.constant.MaterialType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "materials_text")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"materialDate"}, allowGetters = true)
@PrimaryKeyJoinColumn(name = "material_id",columnDefinition = "BIGINT(20)", foreignKey =
@ForeignKey(name = "fk_courses_materials_id_materials_text_id", foreignKeyDefinition = "FOREIGN KEY (material_id) " +
        "REFERENCES courses_materials (material_id) ON DELETE CASCADE ON UPDATE CASCADE"))
public class TextMaterials extends MaterialContent {
    private static final long serialVersionUID = -900298295850119253L;
    @Column(name = "material_text",columnDefinition = "VARCHAR(2000)")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Transient
    @Override
    public MaterialType getMaterialType() {
        return MaterialType.TEXT;
    }

    @Transient

    @Override
    public String toString() {
        return super.toString() + ", TextMaterials{" +
                "text='" + text + '\'' +
                '}';
    }
}
