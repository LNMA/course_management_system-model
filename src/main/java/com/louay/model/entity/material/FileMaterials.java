package com.louay.model.entity.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.louay.model.entity.material.constant.FileType;
import com.louay.model.entity.material.constant.MaterialType;
import org.hibernate.annotations.LazyGroup;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;

@Entity
@Table(name = "materials_files")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"materialDate"}, allowGetters = true)
@PrimaryKeyJoinColumn(name = "material_id", columnDefinition = "BIGINT(20)", foreignKey =
@ForeignKey(name = "fk_courses_materials_id_materials_file_id", foreignKeyDefinition = "FOREIGN KEY (material_id) " +
        "REFERENCES courses_materials (material_id) ON DELETE CASCADE ON UPDATE CASCADE"))
public class FileMaterials extends MaterialContent {
    private static final long serialVersionUID = 5339798688205574320L;
    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "material_file", columnDefinition = "LONGBLOB")
    @LazyGroup("lobs")
    private byte[] file;

    @Column(name = "file_type", columnDefinition = "ENUM('PDF', 'IMAGE')")
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Transient
    public StringBuilder getBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.file));

        return stringBase46;
    }

    @Transient
    @Override
    public MaterialType getMaterialType() {
        return MaterialType.FILE;
    }

    @Transient
    @Override
    public String toString() {
        return "FileMaterials{" +
                "file=" + Arrays.toString(file) +
                ", fileType=" + fileType +
                '}';
    }
}
