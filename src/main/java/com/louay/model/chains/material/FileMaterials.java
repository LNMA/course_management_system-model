package com.louay.model.chains.material;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.util.Objects;

@Component
@Scope("prototype")
public class FileMaterials extends Materials {
    private java.sql.Blob file;

    @Autowired
    public FileMaterials(UserFactoryProducer userFactoryProducer, Courses course) {
        super(userFactoryProducer, course);
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileMaterials)) return false;
        if (!super.equals(o)) return false;
        FileMaterials that = (FileMaterials) o;
        return Objects.equals(getFile(), that.getFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFile());
    }

    @Override
    public String toString() {
        return super.toString() + ", FileMaterials{" +
                "file=" + file +
                '}';
    }
}
