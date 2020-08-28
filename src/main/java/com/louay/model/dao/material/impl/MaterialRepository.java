package com.louay.model.dao.material.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.material.MaterialDao;
import com.louay.model.entity.material.CourseMaterials;
import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.MaterialContent;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MaterialRepository extends CommonDaoImpl<CourseMaterials> implements MaterialDao {

    private static final long serialVersionUID = -676522882754416008L;

    @Override
    public <S extends CourseMaterials> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From CourseMaterials c WHERE c.materialID = :materialID")
                .setParameter("materialID", entity.getMaterialID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends CourseMaterials> S delete(S entity) {
        Class<? extends CourseMaterials> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getMaterialID());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends CourseMaterials> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends CourseMaterials> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getMaterialID());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends CourseMaterials> S findOneById(S entity) {
        Class<? extends CourseMaterials> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getMaterialID());
        return result;
    }

    @Override
    public <S extends CourseMaterials> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends CourseMaterials> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getMaterialID());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public Set<MaterialContent> findFileMaterialWithoutFileByCourseId(MaterialContent materialContent) {
        List<FileMaterials> fileMaterialsList = getEntityManager().createQuery("SELECT fm.materialID, " +
                "fm.fileType, fm.materialDate, fm.uploadDate, fm.user.email, fm.course.courseID, fm.materialName " +
                "FROM FileMaterials fm WHERE fm.course.courseID = :courseId", FileMaterials.class)
                .setParameter("courseId", materialContent.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(fileMaterialsList);
    }

    @Override
    public Set<MaterialContent> findTextMaterialWithoutTextByCourseId(MaterialContent materialContent) {
        List<FileMaterials> fileMaterialsList = getEntityManager().createQuery("SELECT tm.materialID, " +
                "tm.materialDate, tm.uploadDate, tm.user.email, tm.course.courseID, tm.materialName " +
                "FROM TextMaterials tm WHERE tm.course.courseID = :courseId", FileMaterials.class)
                .setParameter("courseId", materialContent.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(fileMaterialsList);
    }

    @Override
    public Set<MaterialContent> findFileMaterialByCourseId(MaterialContent materialContent) {
        List<FileMaterials> fileMaterialsList = getEntityManager().createQuery("SELECT fm " +
                "FROM FileMaterials fm WHERE fm.course.courseID = :courseId", FileMaterials.class)
                .setParameter("courseId", materialContent.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(fileMaterialsList);
    }

    @Override
    public Set<MaterialContent> findTextMaterialByCourseId(MaterialContent materialContent) {
        List<FileMaterials> fileMaterialsList = getEntityManager().createQuery("SELECT tm FROM " +
                "TextMaterials tm WHERE tm.course.courseID = :courseId", FileMaterials.class)
                .setParameter("courseId", materialContent.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(fileMaterialsList);
    }
}
