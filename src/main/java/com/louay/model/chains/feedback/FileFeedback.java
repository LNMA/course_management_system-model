package com.louay.model.chains.feedback;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;

@Component
@Scope("prototype")
public class FileFeedback extends Feedback {
    private byte[] file;
    private String fileExtension;

    @Autowired
    public FileFeedback(Courses course, UserFactoryProducer userFactoryProducer) {
        super(course, userFactoryProducer);
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public StringBuilder getBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.file));

        return stringBase46;
    }

    @Override
    public String toString() {
        return super.toString() + ", FileFeedback{" +
                "file=" + Arrays.toString(file) +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
