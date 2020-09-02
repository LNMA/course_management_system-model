package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.louay.model.entity.feedback.constant.FeedbackType;
import org.hibernate.annotations.LazyGroup;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;

@Entity
@DiscriminatorValue("2")
public class FileFeedback extends FeedbackContent {
    private static final long serialVersionUID = -1809948427771286420L;
    @JsonIgnore
    @Lob
    @Column(name = "file", columnDefinition = "LONGBLOB")
    @Basic(fetch = FetchType.LAZY)
    @LazyGroup("lobs")
    private byte[] file;

    @Column(name = "file_extension", length = 200, columnDefinition = "VARCHAR(200)")
    private String fileExtension;

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

    public StringBuilder getFeedbackFileBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.file));

        return stringBase46;
    }

    @Override
    public FeedbackType getFeedbackType() {
        return FeedbackType.FILE;
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", FileFeedback{" +
                "file=" + Arrays.toString(file) +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}


