package com.louay.model.entity.material;

import com.louay.model.entity.material.constant.MaterialType;

import javax.persistence.*;

@Entity
@Table(name = "materials_text")
@PrimaryKeyJoinColumn(name = "material_id", columnDefinition = "BIGINT(20)", foreignKey =
@ForeignKey(name = "fk_courses_materials_id_materials_text_id", foreignKeyDefinition = "FOREIGN KEY (material_id) " +
        "REFERENCES courses_materials (material_id) ON DELETE CASCADE ON UPDATE CASCADE"))
public class TextMaterials extends MaterialContent {
    private static final long serialVersionUID = -900298295850119253L;
    @Column(name = "material_text", columnDefinition = "VARCHAR(2000)")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
