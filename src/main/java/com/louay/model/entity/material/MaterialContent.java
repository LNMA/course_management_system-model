package com.louay.model.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.material.constant.MaterialType;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"uploadDate"}, allowGetters = true)
@PrimaryKeyJoinColumn(name = "material_id", referencedColumnName = "material_id", columnDefinition = "BIGINT(20)")
public abstract class MaterialContent extends CourseMaterials {
    private static final long serialVersionUID = 3603610715061233435L;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date uploadDate;

    private String materialName;

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Transient
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
