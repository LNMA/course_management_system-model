package com.louay.model.dao.material;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.material.CourseMaterials;
import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.TextMaterials;

import java.util.Set;

public interface MaterialDao extends CommonDao<CourseMaterials>, GenericDao<CourseMaterials> {
    Set<FileMaterials> findFileMaterialWithoutFileByCourseId(FileMaterials fileMaterials);

    Set<TextMaterials> findTextMaterialWithoutTextByCourseId(TextMaterials textMaterials);

    Set<FileMaterials> findFileMaterialByCourseId(FileMaterials fileMaterials);

    Set<TextMaterials> findTextMaterialByCourseId(TextMaterials textMaterials);

    Boolean isFileMaterialExistByCourseId(FileMaterials fileMaterials);

    Boolean isTextMaterialExistByCourseId(TextMaterials textMaterials);
}
