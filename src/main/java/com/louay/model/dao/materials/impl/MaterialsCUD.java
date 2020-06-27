package com.louay.model.dao.materials.impl;

import com.louay.model.chains.material.FileMaterials;
import com.louay.model.chains.material.LinksMaterials;
import com.louay.model.chains.material.Materials;
import com.louay.model.dao.materials.CreateMaterialsDAO;
import com.louay.model.dao.materials.DeleteMaterialsDAO;
import com.louay.model.dao.materials.UpdateMaterialsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class MaterialsCUD implements CreateMaterialsDAO, UpdateMaterialsDAO, DeleteMaterialsDAO {
    private final NamedParameterJdbcTemplate jdbcNamedTemplate;

    @Autowired
    public MaterialsCUD(DataSource dataSource) {
        this.jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long createCoursesMaterials(Materials materials) {
        SqlParameterSource param = buildCoursesMaterialsParameter(materials);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO `courses_materials`(`course_id`) VALUE (:course_id);";

        int result = this.jdbcNamedTemplate.update(query, param, keyHolder);
        if (keyHolder.getKey() != null && result > 0){
            return keyHolder.getKey().longValue();
        }else {
            return (long)-1;
        }
    }

    @Override
    public int createMaterialsFiles(FileMaterials fileMaterials) {
        SqlParameterSource param = buildMaterialsFilesParameter(fileMaterials);

        String query = "INSERT INTO `materials_files`(`material_id`, `user_id`, `material_name`, `material_file`, " +
                "`upload_date`) VALUES (:material_id, :user_id, :material_name, :material_file, :upload_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createMaterialsLinks(LinksMaterials linksMaterials) {
        SqlParameterSource param = buildMaterialsLinksParameter(linksMaterials);

        String query = "INSERT INTO `materials_links`(`material_id`, `user_id`, `material_name`, `material_link`, " +
                "`upload_date`) VALUES (:material_id, :user_id, :material_name, :material_link, :upload_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    private SqlParameterSource buildCoursesMaterialsParameter(Materials materials){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("material_id", materials.getMaterialID(), Types.BIGINT);
        param.addValue("course_id", materials.getCourse().getCourseID(), Types.BIGINT);

        return param;
    }

    private SqlParameterSource buildMaterialsFilesParameter(FileMaterials fileMaterials){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("material_id", fileMaterials.getMaterialID(), Types.BIGINT);
        param.addValue("user_id", fileMaterials.getUser().getEmail(), Types.VARCHAR);
        param.addValue("material_name", fileMaterials.getMaterialName(), Types.VARCHAR);
        param.addValue("material_file", new SqlLobValue(fileMaterials.getFile()), Types.BLOB);
        param.addValue("upload_date", fileMaterials.getUploadDate(), Types.TIMESTAMP);

        return param;
    }

    private SqlParameterSource buildMaterialsLinksParameter(LinksMaterials linksMaterials){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("material_id", linksMaterials.getMaterialID(), Types.BIGINT);
        param.addValue("user_id", linksMaterials.getUser().getEmail(), Types.VARCHAR);
        param.addValue("material_name", linksMaterials.getMaterialName(), Types.VARCHAR);
        param.addValue("material_link", linksMaterials.getLink().toString(), Types.TIMESTAMP);
        param.addValue("upload_date", linksMaterials.getUploadDate(), Types.TIMESTAMP);

        return param;
    }

    @Override
    public int updateCoursesMaterialsByMaterialID(Materials materials) {
        SqlParameterSource param = buildCoursesMaterialsParameter(materials);

        String query = "UPDATE `courses_materials` SET `course_id` = :course_id WHERE `material_id` = :material_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateMaterialsFilesByMaterialID(FileMaterials fileMaterials) {
        SqlParameterSource param = buildMaterialsFilesParameter(fileMaterials);

        String query = "UPDATE `materials_files` SET `user_id` = :user_id, `material_name` = :material_name, " +
                "`material_file` = :material_file, `upload_date` = :upload_date WHERE `material_id` = :material_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateMaterialsLinksByMaterialID(LinksMaterials linksMaterials) {
        SqlParameterSource param = buildMaterialsLinksParameter(linksMaterials);

        String query = "UPDATE `materials_links` SET `user_id` = :user_id, `material_name` = :material_name, " +
                "`material_link` = :material_link, `upload_date` = :upload_date WHERE `material_id` = :material_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteCoursesMaterialsByMaterialID(Materials materials) {
        SqlParameterSource param = buildCoursesMaterialsParameter(materials);

        String query = "DELETE FROM `courses_materials` WHERE `material_id` = :material_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteMaterialsFilesByMaterialID(FileMaterials fileMaterials) {
        SqlParameterSource param = buildMaterialsFilesParameter(fileMaterials);

        String query = "DELETE FROM `materials_files` WHERE `material_id` = :material_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteMaterialsLinksByMaterialID(LinksMaterials linksMaterials) {
        SqlParameterSource param = buildMaterialsLinksParameter(linksMaterials);

        String query = "DELETE FROM `materials_links` WHERE `material_id` = :material_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }
}
