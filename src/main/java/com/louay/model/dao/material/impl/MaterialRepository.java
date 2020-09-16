package com.louay.model.dao.material.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.material.MaterialDao;
import com.louay.model.entity.material.CourseMaterials;
import com.louay.model.entity.material.FileMaterials;
import com.louay.model.entity.material.TextMaterials;
import com.louay.model.entity.wrapper.CourseSearch;
import com.louay.model.entity.wrapper.FileMaterialWithOutFile;
import com.louay.model.entity.wrapper.GeneralSearch;
import com.louay.model.entity.wrapper.MaterialWithOutContent;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MaterialRepository extends CommonDaoImpl<CourseMaterials> implements MaterialDao {
    private static final long serialVersionUID = -2055998155570229002L;

    @Override
    public <S extends CourseMaterials> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From CourseMaterials c WHERE c.materialID = :materialID")
                .setParameter("materialID", entity.getMaterialID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public Boolean isFileMaterialExistByCourseId(FileMaterials fileMaterials) {
        return !getEntityManager().createQuery("SELECT fm From FileMaterials fm WHERE fm.course.courseID = :courseId")
                .setParameter("courseId", fileMaterials.getCourse().getCourseID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public Boolean isTextMaterialExistByCourseId(TextMaterials textMaterials) {
        return !getEntityManager().createQuery("SELECT tm From TextMaterials tm WHERE tm.course.courseID = :courseId")
                .setParameter("courseId", textMaterials.getCourse().getCourseID())
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
    public Set<FileMaterialWithOutFile> findFileMaterialWithoutFileByCourseId(FileMaterials fileMaterials) {
        List<FileMaterialWithOutFile> fileMaterialsList = getEntityManager().createQuery("SELECT NEW " +
                "com.louay.model.entity.wrapper.FileMaterialWithOutFile(fm.materialID, " +
                "fm.materialName, fm.materialDate, fm.uploadDate, fm.user.email, fm.course.courseID, fm.fileType) " +
                "FROM FileMaterials fm WHERE fm.course.courseID = :courseId", FileMaterialWithOutFile.class)
                .setParameter("courseId", fileMaterials.getCourse().getCourseID())
                .getResultList();

        return new HashSet<>(fileMaterialsList);
    }

    @Override
    public Set<MaterialWithOutContent> findTextMaterialWithoutTextByCourseId(TextMaterials textMaterials) {
        List<MaterialWithOutContent> textMaterialsList = getEntityManager().createQuery("SELECT NEW " +
                "com.louay.model.entity.wrapper.MaterialWithOutContent(tm.materialID, " +
                "tm.materialName , tm.materialDate, tm.uploadDate, tm.user.email, tm.course.courseID)" +
                "FROM TextMaterials tm WHERE tm.course.courseID = :courseId", MaterialWithOutContent.class)
                .setParameter("courseId", textMaterials.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(textMaterialsList);
    }

    @Override
    public Set<FileMaterials> findFileMaterialByCourseId(FileMaterials fileMaterials) {
        List<FileMaterials> fileMaterialsList = getEntityManager().createQuery("SELECT fm " +
                "FROM FileMaterials fm WHERE fm.course.courseID = :courseId", FileMaterials.class)
                .setParameter("courseId", fileMaterials.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(fileMaterialsList);
    }

    @Override
    public Set<TextMaterials> findTextMaterialByCourseId(TextMaterials textMaterials) {
        List<TextMaterials> textMaterialsList = getEntityManager().createQuery("SELECT tm FROM " +
                "TextMaterials tm WHERE tm.course.courseID = :courseId", TextMaterials.class)
                .setParameter("courseId", textMaterials.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(textMaterialsList);
    }

    @Override
    public Set<CourseMaterials> findCourseMaterialsLikePagination(GeneralSearch generalSearch) {
        String key = generalSearch.getKey() + "%";
        int firstResultValue = (generalSearch.getPageNumber() - 1) * generalSearch.getPageSize();
        List<CourseMaterials> courseMaterialsList = getEntityManager().createQuery("SELECT cm FROM " +
                "CourseMaterials cm JOIN FETCH cm.course WHERE cm.user.email LIKE :email", CourseMaterials.class)
                .setParameter("email", key)
                .setFirstResult(firstResultValue)
                .setMaxResults(generalSearch.getPageSize())
                .getResultList();

        return new HashSet<>(courseMaterialsList);
    }

    @Override
    public Long getCountCourseMaterialsLikePagination(GeneralSearch generalSearch) {
        String key = generalSearch.getKey() + "%";
        return getEntityManager().createQuery("SELECT COUNT(cm) FROM " +
                "CourseMaterials cm WHERE cm.user.email LIKE :email", Long.class)
                .setParameter("email", key)
                .setMaxResults(1)
                .getResultList()
                .get(0);
    }

    @Override
    public Set<CourseMaterials> findCourseMaterialEagerCourseByMaterialId(Iterable<CourseMaterials> materialsIterable) {
        Set<CourseMaterials> courseMaterialsSet = new HashSet<>();
        for (CourseMaterials cm : materialsIterable) {
            CourseMaterials courseMaterials = getEntityManager().createQuery("SELECT cm FROM CourseMaterials cm " +
                    "JOIN FETCH cm.course WHERE cm.materialID = :materialID", CourseMaterials.class)
                    .setParameter("materialID", cm.getMaterialID())
                    .getSingleResult();
            courseMaterialsSet.add(courseMaterials);
        }
        return courseMaterialsSet;
    }

    @Override
    public Set<CourseMaterials> findCourseMaterialsLikeToCourseSearch(CourseSearch courseSearch) {
        String key = courseSearch.getKey() + "%";
        List<CourseMaterials> courseMaterialsList = getEntityManager().createQuery("SELECT cm FROM " +
                "CourseMaterials cm JOIN FETCH cm.course WHERE cm.user.email LIKE :email " +
                "AND cm.course.courseID = :courseID", CourseMaterials.class)
                .setParameter("email", key)
                .setParameter("courseID", courseSearch.getCourses().getCourseID())
                .getResultList();

        return new HashSet<>(courseMaterialsList);
    }
}
