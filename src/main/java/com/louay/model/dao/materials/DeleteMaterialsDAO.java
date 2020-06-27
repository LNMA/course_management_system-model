package com.louay.model.dao.materials;

import com.louay.model.chains.material.FileMaterials;
import com.louay.model.chains.material.LinksMaterials;
import com.louay.model.chains.material.Materials;

public interface DeleteMaterialsDAO {

    int deleteCoursesMaterialsByMaterialID(Materials materials);

    int deleteMaterialsFilesByMaterialID(FileMaterials fileMaterials);

    int deleteMaterialsLinksByMaterialID(LinksMaterials linksMaterials);
}
