package com.louay.model.service.material.impl;

import com.louay.model.dao.material.MaterialDao;
import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.TextMaterials;
import com.louay.model.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class MaterialCrudService implements MaterialService, Serializable {
    private static final long serialVersionUID = 571082965547024039L;
    private final MaterialDao materialDao;

    @Autowired
    public MaterialCrudService(MaterialDao materialDao) {
        if (materialDao == null){
            throw new IllegalArgumentException("DAO cannot be null at MaterialCrudService.class");
        }
        this.materialDao = materialDao;
    }

    private MaterialDao getMaterialDao() {
        return materialDao;
    }

    @Transactional
    @Override
    public TextMaterials createTextMaterials(TextMaterials textMaterials) {
        return getMaterialDao().save(textMaterials);
    }

    @Transactional
    @Override
    public TextMaterials deleteTextMaterialsByMaterialId(TextMaterials textMaterials) {
        return getMaterialDao().delete(textMaterials);
    }

    @Transactional
    @Override
    public TextMaterials updateTextMaterials(TextMaterials textMaterials) {
        return getMaterialDao().update(textMaterials);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public TextMaterials findTextMaterialsByMaterialId(TextMaterials textMaterials) {
        return getMaterialDao().findOneById(textMaterials);
    }

    @Transactional
    @Override
    public FileMaterials createFileMaterials(FileMaterials fileMaterials) {
        return getMaterialDao().save(fileMaterials);
    }

    @Transactional
    @Override
    public FileMaterials deleteFileMaterialsByMaterialId(FileMaterials fileMaterials) {
        return getMaterialDao().delete(fileMaterials);
    }

    @Transactional
    @Override
    public FileMaterials updateFileMaterials(FileMaterials fileMaterials) {
        return getMaterialDao().update(fileMaterials);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public FileMaterials findFileMaterialsByMaterialId(FileMaterials fileMaterials) {
        return getMaterialDao().findOneById(fileMaterials);
    }
}
