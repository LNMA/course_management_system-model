package com.louay.model.dao.status.impl;

import com.louay.model.chains.status.UserStatus;
import com.louay.model.chains.status.CourseJoinStatus;
import com.louay.model.chains.status.SignInStatus;
import com.louay.model.dao.status.CreateStatusDAO;
import com.louay.model.dao.status.DeleteStatusDAO;
import com.louay.model.dao.status.UpdateStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class StatusCUD implements CreateStatusDAO, UpdateStatusDAO, DeleteStatusDAO {
    private final NamedParameterJdbcTemplate jdbcNamedTemplate;
    private final ApplicationContext context;

    @Autowired
    public StatusCUD(DataSource dataSource, @Qualifier("buildAnnotationContextModel") ApplicationContext context) {
        this.jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.context = context;
    }

    @Override
    public Long createUsersSignInDate(SignInStatus signInStatus) {
        SqlParameterSource param = buildUserSignInParameter(signInStatus);
        KeyHolder keyHolder = (KeyHolder) this.context.getBean("buildKeyHolder");

        final String query = "INSERT INTO `users_sign_in_date`(`user_id`, `sign_in_date`) VALUES (:user_id, :sign_in_date);";

        int result = this.jdbcNamedTemplate.update(query, param, keyHolder);
        if (keyHolder.getKey() != null && result > 0) {
            return keyHolder.getKey().longValue();
        } else {
            return (long) -1;
        }
    }

    @Override
    public int createUsersStatus(UserStatus userStatus) {
        SqlParameterSource param = buildUserStatusParameter(userStatus);

        final String query = "INSERT INTO `users_status`(`user_id`, `is_valid`, `is_online`) VALUES " +
                "(:user_id, :is_valid, :is_online);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createUsersCourseJoin(CourseJoinStatus courseJoinStatus) {
        SqlParameterSource param = buildUserCourseJoinParameter(courseJoinStatus);

        final String query = "INSERT INTO `users_course_join`(`users_id`, `is_busy`, `join_date`) VALUES " +
                "(:users_id, :is_busy, :join_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    private SqlParameterSource buildUserSignInParameter(SignInStatus signInStatus) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("user_sign_in_id", signInStatus.getUserSignInID(), Types.BIGINT);
        param.addValue("user_id", signInStatus.getUser().getEmail(), Types.VARCHAR);
        param.addValue("sign_in_date", signInStatus.getSignInDate(), Types.TIMESTAMP);

        return param;
    }

    private SqlParameterSource buildUserStatusParameter(UserStatus userStatus) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("user_id", userStatus.getUser().getEmail(), Types.VARCHAR);
        param.addValue("is_online", userStatus.getOnline(), Types.TINYINT);
        param.addValue("is_valid", userStatus.getValid(), Types.TINYINT);

        return param;
    }

    private SqlParameterSource buildUserCourseJoinParameter(CourseJoinStatus courseJoinStatus) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("users_id", courseJoinStatus.getUser().getEmail(), Types.VARCHAR);
        param.addValue("is_busy", courseJoinStatus.getBusy(), Types.TINYINT);
        param.addValue("join_date", courseJoinStatus.getJoinDate(), Types.TIMESTAMP);

        return param;
    }

    @Override
    public int updateUsersSignInDateByUserSignInID(SignInStatus signInStatus) {
        SqlParameterSource param = buildUserSignInParameter(signInStatus);

        final String query = "UPDATE `users_sign_in_date` SET `user_id` = :user_id, `sign_in_date` = :sign_in_date " +
                "WHERE `user_sign_in_id` = :user_sign_in_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateUsersStatusByUserID(UserStatus userStatus) {
        SqlParameterSource param = buildUserStatusParameter(userStatus);

        final String query = "UPDATE `users_status` SET `is_online` = :is_online, `is_valid` = :is_valid " +
                "WHERE `user_id` = :user_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateUsersCourseJoinByUserID(CourseJoinStatus courseJoinStatus) {
        SqlParameterSource param = buildUserCourseJoinParameter(courseJoinStatus);

        final String query = "UPDATE `users_course_join` SET `is_busy` = :is_busy, `join_date` = :join_date " +
                "WHERE `users_id` = :users_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteUsersSignInDateByUserSignInID(SignInStatus signInStatus) {
        SqlParameterSource param = buildUserSignInParameter(signInStatus);

        final String query = "DELETE FROM `users_sign_in_date` WHERE `user_sign_in_id` = :user_sign_in_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteUsersStatusByUserID(UserStatus userStatus) {
        SqlParameterSource param = buildUserStatusParameter(userStatus);

        final String query = "DELETE FROM `users_status` WHERE `user_id` = :user_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteUsersCourseJoinByUserID(CourseJoinStatus courseJoinStatus) {
        SqlParameterSource param = buildUserCourseJoinParameter(courseJoinStatus);

        final String query = "DELETE FROM `users_course_join` WHERE `users_id` = :users_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }
}
