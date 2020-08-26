package com.louay.model.entity.feedback;

import com.louay.model.entity.feedback.constant.FeedbackType;
import org.hibernate.annotations.LazyGroup;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@DiscriminatorValue("3")
public class FileMessageFeedback extends FeedbackContent {
    private static final long serialVersionUID = 200244160784858021L;
    @Column(name = "post_message", length = 1000, columnDefinition = "VARCHAR(1000)")
    private String postMessage;

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

    @Transient
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
