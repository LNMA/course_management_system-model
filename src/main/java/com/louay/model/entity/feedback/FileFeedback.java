package com.louay.model.entity.feedback;

import com.louay.model.entity.feedback.constant.FeedbackType;
import org.hibernate.annotations.LazyGroup;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@DiscriminatorValue("2")
public class FileFeedback extends FeedbackContent {
    private static final long serialVersionUID = -3101064198691521124L;
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

    @Transient
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


