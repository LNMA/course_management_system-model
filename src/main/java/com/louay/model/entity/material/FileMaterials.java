package com.louay.model.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.material.constant.MaterialType;
import org.hibernate.annotations.LazyGroup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "materials_files")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"materialDate"}, allowGetters = true)
@PrimaryKeyJoinColumn(name = "material_id",columnDefinition = "BIGINT(20)", foreignKey =
@ForeignKey(name = "fk_courses_materials_id_materials_file_id", foreignKeyDefinition = "FOREIGN KEY (material_id) " +
        "REFERENCES courses_materials (material_id) ON DELETE CASCADE ON UPDATE CASCADE"))
public class FileMaterials extends MaterialContent {
    private static final long serialVersionUID = 9063425071236162493L;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "material_file", columnDefinition = "LONGBLOB")
    @LazyGroup("lobs")
    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Transient
    @Override
    public MaterialType getMaterialType() {
        return MaterialType.FILE;
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", FileCourseMaterials{" +
                "file=" + Arrays.toString(file) +
                '}';
    }
}
