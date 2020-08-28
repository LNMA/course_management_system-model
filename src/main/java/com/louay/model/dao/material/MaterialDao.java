package com.louay.model.dao.material;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.material.CourseMaterials;
import com.louay.model.entity.material.MaterialContent;

import java.util.Set;

public interface MaterialDao extends CommonDao<CourseMaterials>, GenericDao<CourseMaterials> {
    Set<MaterialContent> findFileMaterialWithoutFileByCourseId(MaterialContent materialContent);

    Set<MaterialContent> findTextMaterialWithoutTextByCourseId(MaterialContent materialContent);

    Set<MaterialContent> findFileMaterialByCourseId(MaterialContent materialContent);

    Set<MaterialContent> findTextMaterialByCourseId(MaterialContent materialContent);
}
