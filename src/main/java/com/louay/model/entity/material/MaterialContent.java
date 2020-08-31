package com.louay.model.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.material.constant.MaterialType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Calendar;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"uploadDate"}, allowGetters = true)
@PrimaryKeyJoinColumn(name = "material_id", referencedColumnName = "material_id", columnDefinition = "BIGINT(20)")
public abstract class MaterialContent extends CourseMaterials {
    private static final long serialVersionUID = -6132765251961707477L;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "upload_date", columnDefinition = "TIMESTAMP(0)")
    private Calendar uploadDate;

    @Column(name = "material_name", columnDefinition = "VARCHAR(200)")
    private String materialName;

    public Calendar getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Calendar uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUploadDateString() {
        return this.uploadDate.getTime().toString();
    }

    abstract public MaterialType getMaterialType();

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", MaterialContent{" +
                "uploadDate=" + uploadDate +
                ", materialName='" + materialName + '\'' +
                '}';
    }
}
