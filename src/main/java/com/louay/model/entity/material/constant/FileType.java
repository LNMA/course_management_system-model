package com.louay.model.entity.material.constant;

public enum FileType {
    PDF("PDF"), IMAGE("IMAGE");

    private final String fileType;

    FileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
