package com.louay.model.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.louay.model.entity.material.constant.MaterialType;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class MaterialWithOutContent implements Serializable, Comparable<MaterialWithOutContent> {
    private static final long serialVersionUID = 5646634569301716904L;
    private Long materialID;
    private String materialName;
    private Calendar materialDate;
    private Calendar uploadDate;
    private String usersEmail;
    private Long coursesId;

    public MaterialWithOutContent(Long materialID, String materialName, Calendar materialDate, Calendar uploadDate, String usersEmail, Long coursesId) {
        this.materialID = materialID;
        this.materialName = materialName;
        this.materialDate = materialDate;
        this.uploadDate = uploadDate;
        this.usersEmail = usersEmail;
        this.coursesId = coursesId;
    }

    public Long getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Long materialID) {
        this.materialID = materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @JsonIgnore
    public Calendar getMaterialDate() {
        return materialDate;
    }

    public void setMaterialDate(Calendar materialDate) {
        this.materialDate = materialDate;
    }

    @JsonIgnore
    public Calendar getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Calendar uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }

    public Long getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Long coursesId) {
        this.coursesId = coursesId;
    }

    public String getMaterialDateString() {
        return this.materialDate.getTime().toString();
    }

    public String getUploadDateString() {
        return this.uploadDate.getTime().toString();
    }

    public MaterialType getMaterialType() {
        return MaterialType.TEXT;
    }

    @Override
    public int compareTo(MaterialWithOutContent o) {
        if (this.materialDate == null || o.getMaterialDate() == null) {
            return 0;
        }
        if (this.materialDate.after(o.getMaterialDate())) {
            return -1;
        }
        if (this.materialDate.before(o.getMaterialDate())) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileMaterialWithOutFile that = (FileMaterialWithOutFile) o;
        return getMaterialID().equals(that.getMaterialID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterialID());
    }

    @Override
    public String toString() {
        return "MaterialWithOutContent{" +
                "materialID=" + materialID +
                ", materialName='" + materialName + '\'' +
                ", materialDate=" + materialDate +
                ", uploadDate=" + uploadDate +
                ", usersEmail='" + usersEmail + '\'' +
                ", coursesId=" + coursesId +
                '}';
    }
}
