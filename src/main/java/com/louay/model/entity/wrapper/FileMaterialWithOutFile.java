package com.louay.model.entity.wrapper;

import com.louay.model.entity.material.constant.FileType;
import com.louay.model.entity.material.constant.MaterialType;

import java.util.Calendar;

public class FileMaterialWithOutFile extends MaterialWithOutContent {
    private static final long serialVersionUID = -5999424346785528502L;
    private FileType fileType;

    public FileMaterialWithOutFile(Long materialID, String materialName, Calendar materialDate, Calendar uploadDate, String usersEmail, Long coursesId, FileType fileType) {
        super(materialID, materialName, materialDate, uploadDate, usersEmail, coursesId);
        this.fileType = fileType;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public MaterialType getMaterialType() {
        return MaterialType.FILE;
    }

    @Override
    public String toString() {
        return super.toString() + ", FileMaterialWithOutFile{" +
                "fileType=" + fileType +
                '}';
    }
}
