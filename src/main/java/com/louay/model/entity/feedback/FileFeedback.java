package com.louay.model.entity.feedback;

import com.louay.model.entity.feedback.constant.FeedbackType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "feedback_content")
@DiscriminatorValue("1")
public class FileFeedback extends FeedbackContent {
    private static final long serialVersionUID = -3101064198691521124L;
    @Lob
    @Column(name = "file")
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    @Column(name = "file_extension", length = 200)
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
        return "FileFeedback{" +
                "file=" + Arrays.toString(file) +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
