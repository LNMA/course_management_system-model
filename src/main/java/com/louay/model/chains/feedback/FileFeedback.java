package com.louay.model.chains.feedback;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.util.Objects;

@Component
@Scope("prototype")
public class FileFeedback extends Feedback {
    private java.sql.Blob file;
    private String fileExtension;

    @Autowired
    public FileFeedback(Courses course, UserFactoryProducer userFactoryProducer) {
        super(course, userFactoryProducer);
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileFeedback)) return false;
        if (!super.equals(o)) return false;
        FileFeedback that = (FileFeedback) o;
        return getFile().equals(that.getFile()) &&
                getFileExtension().compareTo(that.getFileExtension()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFile(), getFileExtension());
    }

    @Override
    public String toString() {
        return super.toString() + ", FileFeedback{" +
                "file=" + file +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
