package com.louay.model.chains.material;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.Users;
import com.louay.model.chains.users.factory.UserFactoryProducer;

import java.sql.Timestamp;
import java.util.Objects;

public abstract class Materials {
    private Double materialID;
    private Courses course;
    private Users user;
    private UserFactoryProducer userFactoryProducer;
    private String materialName;
    private java.sql.Timestamp uploadDate;

    public Materials(UserFactoryProducer userFactoryProducer, Courses course) {
        this.userFactoryProducer = userFactoryProducer;
        this.course = course;
    }

    public Double getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Double materialID) {
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

    public UserFactoryProducer getUserFactoryProducer() {
        return userFactoryProducer;
    }

    public void setUserFactoryProducer(UserFactoryProducer userFactoryProducer) {
        this.userFactoryProducer = userFactoryProducer;
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
        return getMaterialID().compareTo(materials.getMaterialID()) == 0;
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
