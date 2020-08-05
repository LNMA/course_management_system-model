package com.louay.model.service.material;

import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.TextMaterials;

public interface MaterialService {
    TextMaterials createTextMaterials(TextMaterials textMaterials);

    TextMaterials deleteTextMaterialsByMaterialId(TextMaterials textMaterials);

    TextMaterials updateTextMaterials(TextMaterials textMaterials);

    TextMaterials findTextMaterialsByMaterialId(TextMaterials textMaterials);

    FileMaterials createFileMaterials(FileMaterials fileMaterials);

    FileMaterials deleteFileMaterialsByMaterialId(FileMaterials fileMaterials);

    FileMaterials updateFileMaterials(FileMaterials fileMaterials);

    FileMaterials findFileMaterialsByMaterialId(FileMaterials fileMaterials);
}
