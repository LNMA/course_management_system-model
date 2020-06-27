package com.louay.model.chains.material;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.Users;
import com.louay.model.chains.users.constant.UserRole;
import com.louay.model.chains.users.factory.UserFactoryProducer;

import java.sql.Timestamp;
import java.util.Objects;

public abstract class Materials {
    private Long materialID;
    private Courses course;
    private Users user;
    private final UserFactoryProducer userFactoryProducer;
    private String materialName;
    private java.sql.Timestamp uploadDate;

    public Materials(UserFactoryProducer userFactoryProducer, Courses course) {
        this.userFactoryProducer = userFactoryProducer;
        this.course = course;
    }

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
        if (this.user == null){
            throw new RuntimeException("User must be initialized by using setUserInstance() method.");
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUserInstance(UserRole userRole){
        this.user = this.userFactoryProducer.getFactory(userRole).getUsers();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materials materials = (Materials) o;
        return getMaterialID().equals(materials.getMaterialID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterialID());
    }

    @Override
    public String toString() {
        return "Materials{" +
                "materialID=" + materialID +
                ", course=" + course +
                ", user=" + user +
                ", materialName='" + materialName + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
