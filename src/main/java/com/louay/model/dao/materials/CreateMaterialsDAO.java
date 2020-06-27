package com.louay.model.dao.materials;

import com.louay.model.chains.material.FileMaterials;
import com.louay.model.chains.material.LinksMaterials;
import com.louay.model.chains.material.Materials;

public interface CreateMaterialsDAO {

    Long createCoursesMaterials(Materials materials);

    int createMaterialsFiles(FileMaterials fileMaterials);

    int createMaterialsLinks(LinksMaterials linksMaterials);
}
