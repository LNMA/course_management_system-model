package com.louay.model.dao.materials;

import com.louay.model.chains.material.FileMaterials;
import com.louay.model.chains.material.LinksMaterials;
import com.louay.model.chains.material.Materials;

public interface DeleteMaterialsDAO {

    int deleteCoursesMaterials(Materials materials);

    int deleteMaterialsFiles(FileMaterials fileMaterials);

    int deleteMaterialsLinks(LinksMaterials linksMaterials);
}
