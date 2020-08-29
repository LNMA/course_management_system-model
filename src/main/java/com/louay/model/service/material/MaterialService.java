package com.louay.model.service.material;

import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.MaterialContent;
import com.louay.model.entity.material.TextMaterials;

import java.util.Set;

public interface MaterialService {
    TextMaterials createTextMaterials(TextMaterials textMaterials);

    TextMaterials deleteTextMaterialsByMaterialId(TextMaterials textMaterials);

    TextMaterials updateTextMaterials(TextMaterials textMaterials);

    TextMaterials findTextMaterialsByMaterialId(TextMaterials textMaterials);

    FileMaterials createFileMaterials(FileMaterials fileMaterials);

    FileMaterials deleteFileMaterialsByMaterialId(FileMaterials fileMaterials);

    FileMaterials updateFileMaterials(FileMaterials fileMaterials);

    FileMaterials findFileMaterialsByMaterialId(FileMaterials fileMaterials);

    Boolean isFileMaterialExistByCourseId(FileMaterials fileMaterials);

    Boolean isTextMaterialExistByCourseId(TextMaterials textMaterials);

    Set<FileMaterials> findFileMaterialWithoutFileByCourseId(FileMaterials fileMaterials);

    Set<TextMaterials> findTextMaterialWithoutTextByCourseId(TextMaterials textMaterials);

    Set<FileMaterials> findFileMaterialByCourseId(FileMaterials fileMaterials);

    Set<TextMaterials> findTextMaterialByCourseId(TextMaterials textMaterials);
}
