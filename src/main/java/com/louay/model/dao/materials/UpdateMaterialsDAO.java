package com.louay.model.dao.materials;

import com.louay.model.chains.material.FileMaterials;
import com.louay.model.chains.material.LinksMaterials;
import com.louay.model.chains.material.Materials;

public interface UpdateMaterialsDAO {

    int updateCoursesMaterials(Materials materials);

    int updateMaterialsFiles(FileMaterials fileMaterials);

    int updateMaterialsLinks(LinksMaterials linksMaterials);
}
