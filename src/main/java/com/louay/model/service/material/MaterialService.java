package com.louay.model.service.material;

import com.louay.model.entity.material.CourseMaterials;
import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.TextMaterials;
import com.louay.model.entity.wrapper.FileMaterialWithOutFile;
import com.louay.model.entity.wrapper.GeneralSearch;
import com.louay.model.entity.wrapper.MaterialWithOutContent;

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

    Set<FileMaterialWithOutFile> findFileMaterialWithoutFileByCourseId(FileMaterials fileMaterials);

    Set<MaterialWithOutContent> findTextMaterialWithoutTextByCourseId(TextMaterials textMaterials);

    Set<FileMaterials> findFileMaterialByCourseId(FileMaterials fileMaterials);

    Set<TextMaterials> findTextMaterialByCourseId(TextMaterials textMaterials);

    Set<CourseMaterials> findCourseMaterialsLikeForSearch(GeneralSearch generalSearch);

    Long getCountCourseMaterialsLikeForSearch(GeneralSearch generalSearch);

    Set<CourseMaterials> findCourseMaterialsByMaterialId(Iterable<CourseMaterials> materialsIterable);
}
