package com.louay.model.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.louay.model.entity.feedback.constant.FeedbackType;
import org.hibernate.annotations.LazyGroup;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;

@Entity
@DiscriminatorValue("3")
public class FileMessageFeedback extends FeedbackContent {
    private static final long serialVersionUID = 3317661533358191538L;
    @Column(name = "post_message", length = 1000, columnDefinition = "VARCHAR(1000)")
    private String postMessage;

    @JsonIgnore
    @Lob
    @Column(name = "file", columnDefinition = "LONGBLOB")
    @Basic(fetch = FetchType.LAZY)
    @LazyGroup("lobs")
    private byte[] file;

    @Column(name = "file_extension", length = 200, columnDefinition = "VARCHAR(200)")
    private String fileExtension;

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
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

    public StringBuilder getFeedbackFileBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.file));

        return stringBase46;
    }

    @Override
    public FeedbackType getFeedbackType() {
        return FeedbackType.ALL;
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", FileMessageFeedback{" +
                "postMessage='" + postMessage + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
