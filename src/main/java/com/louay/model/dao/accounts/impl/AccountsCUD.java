package com.louay.model.dao.accounts.impl;

import com.louay.model.chains.users.*;
import com.louay.model.dao.accounts.CreateAccountsDAO;
import com.louay.model.dao.accounts.DeleteAccountsDAO;
import com.louay.model.dao.accounts.UpdateAccountsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class AccountsCUD implements CreateAccountsDAO, UpdateAccountsDAO, DeleteAccountsDAO {
    private final NamedParameterJdbcTemplate jdbcNamedTemplate;
    private final ApplicationContext context;

    @Autowired
    public AccountsCUD(DataSource dataSource, @Qualifier("buildAnnotationContextModel") ApplicationContext context) {
        this.jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.context = context;
    }

    @Override
    public int createUsers(Accounts account) {
        SqlParameterSource param = buildUsersParameter(account);

        final String query = "INSERT INTO `users`(`email`, `forename`, `surname`, `password`, `join_date`) VALUES " +
                "(:email, :forename, :surname, :password, :join_date);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createUsersDetails(Users user) {
        SqlParameterSource param = buildUsersDetailsParameter(user);

        final String query = "INSERT INTO `users_details`(`user_id`, `gender`, `phone`, `birthday`, `country`, `state`, `address`) " +
                "VALUES (:user_id, :gender, :phone, :birthday, :country, :state, :address);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createStudentsDetails(Student student) {
        SqlParameterSource param = buildStudentsDetailsParameter(student);

        final String query = "INSERT INTO `students_details`(`student_id`, `headline`, `interests`) VALUES " +
                "(:student_id, :headline, :intrests);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createInstructorsDetails(Instructor instructor) {
        SqlParameterSource param = buildInstructorsDetailsParameter(instructor);

        final String query = "INSERT INTO `instructors_details`(`instructors_id`, `headline`, `specialty`, `nickname`, " +
                "`portfolio`, `profile_visibility`) VALUES (:instructors_id, :headline, :specialty, :nickname, " +
                ":portfolio, :profile_visibility);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createUsersRoles(AccountsRoles accountsRoles) {
        SqlParameterSource param = buildUsersRolesParameter(accountsRoles);

        final String query = "INSERT INTO `users_roles`(`user_id`, `role_id`) VALUES (:user_id, :role_id);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public Long createRole(AccountsRoles accountsRoles) {
        SqlParameterSource param = buildRolesParameter(accountsRoles);
        KeyHolder keyHolder = (KeyHolder) this.context.getBean("buildKeyHolder");

        final String query = "INSERT INTO `roles`(`role_name`) VALUES (:role_name);";

        int result = this.jdbcNamedTemplate.update(query, param, keyHolder);
        if (keyHolder.getKey() != null && result > 0) {
            return keyHolder.getKey().longValue();
        } else {
            return (long) -1;
        }
    }

    @Override
    public int createUsersProfilePicture(Accounts account) {
        SqlParameterSource param = buildUserProfilePicture(account);

        final String query = "INSERT INTO `users_profile_picture`(`user_id`, `upload_date`, `picture`) VALUES " +
                "(:user_id, :upload_date, :picture);";

        return this.jdbcNamedTemplate.update(query, param);
    }

    private SqlParameterSource buildUsersParameter(Accounts account) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("email", account.getEmail(), Types.VARCHAR);
        param.addValue("forename", account.getForename(), Types.VARCHAR);
        param.addValue("surname", account.getSurname(), Types.VARCHAR);
        param.addValue("password", account.getPassword(), Types.VARCHAR);
        param.addValue("join_date", account.getJoinDate(), Types.TIMESTAMP);

        return param;
    }

    private SqlParameterSource buildUsersDetailsParameter(Users user) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("user_id", user.getEmail(), Types.VARCHAR);
        param.addValue("gender", user.getGender(), Types.VARCHAR);
        param.addValue("phone", user.getPhone(), Types.INTEGER);
        param.addValue("birthday", user.getBirthday(), Types.DATE);
        param.addValue("country", user.getCountry(), Types.VARCHAR);
        param.addValue("state", user.getState(), Types.VARCHAR);
        param.addValue("address", user.getAddress(), Types.VARCHAR);

        return param;
    }

    private SqlParameterSource buildStudentsDetailsParameter(Student student) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("student_id", student.getEmail(), Types.VARCHAR);
        param.addValue("headline", student.getHeadline(), Types.VARCHAR);
        param.addValue("interests", student.getInterests(), Types.VARCHAR);

        return param;
    }

    private SqlParameterSource buildInstructorsDetailsParameter(Instructor instructor) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("instructors_id", instructor.getEmail(), Types.VARCHAR);
        param.addValue("headline", instructor.getHeadline(), Types.VARCHAR);
        param.addValue("specialty", instructor.getSpecialty(), Types.VARCHAR);
        param.addValue("nickname", instructor.getNickname(), Types.VARCHAR);
        param.addValue("portfolio", instructor.getPortfolio(), Types.VARCHAR);
        param.addValue("profile_visibility", instructor.getProfileVisibility(), Types.VARCHAR);

        return param;
    }

    private SqlParameterSource buildUsersRolesParameter(AccountsRoles accountsRoles) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("user_id", accountsRoles.getAccounts().getEmail(), Types.VARCHAR);
        param.addValue("role_id", accountsRoles.getRoleID(), Types.BIGINT);

        return param;
    }

    private SqlParameterSource buildRolesParameter(AccountsRoles accountsRoles) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("role_id", accountsRoles.getRoleID(), Types.BIGINT);
        param.addValue("role_name", accountsRoles.getRoleName(), Types.VARCHAR);

        return param;
    }

    private SqlParameterSource buildUserProfilePicture(Accounts account) {
        MapSqlParameterSource param = (MapSqlParameterSource) this.context.getBean("buildMapParameter");
        param.addValue("user_id", account.getEmail());
        param.addValue("upload_date", account.getUploadPicDate(), Types.TIMESTAMP);
        param.addValue("picture", new SqlLobValue(account.getPicture()), Types.BLOB);

        return param;
    }

    @Override
    public int updateUsersByEmail(Accounts account) {
        SqlParameterSource param = buildUsersParameter(account);

        final String query = "UPDATE `users` SET `forename` = :forename, `surname` = :surname, `password` = :password, " +
                "`join_date`= :joim_date WHERE `email` = :email;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateUsersDetailsByUserID(Users user) {
        SqlParameterSource param = buildUsersDetailsParameter(user);

        final String query = "UPDATE `users_details` SET `gender` = :gender, `phone` = :phone, `birthday` = :birthday, " +
                "`country`= :country, `state` = :state, `address` = :address WHERE `user_id` = :user_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateStudentsDetailsByStudentID(Student student) {
        SqlParameterSource param = buildStudentsDetailsParameter(student);

        final String query = "UPDATE `students_details` SET `headline` = :headline, `interests` = :interests WHERE " +
                "`student_id` = :student_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateInstructorsDetailsByInstructorID(Instructor instructor) {
        SqlParameterSource param = buildInstructorsDetailsParameter(instructor);

        final String query = "UPDATE `instructors_details` SET `headline` = :headline, `specialty` = :specialty, " +
                "`nickname` = :nickname, `portfolio` = :portfolio, `profile_visibility`= :profile_visibility " +
                "WHERE `instructors_id` = :instructors_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateRoleByRoleID(AccountsRoles accountsRoles) {
        SqlParameterSource param = buildRolesParameter(accountsRoles);

        final String query = "UPDATE `roles` SET `role_name` = :role_name WHERE `role_id` = :role_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int updateUsersProfilePictureByUserID(Accounts account) {
        SqlParameterSource param = buildUserProfilePicture(account);

        final String query = "UPDATE `users_profile_picture` SET `upload_date` = :upload_date, `picture` = :picture " +
                "WHERE `user_id` = :user_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteUsersByEmail(Accounts account) {
        SqlParameterSource param = buildUsersParameter(account);

        final String query = "DELETE FROM `users` WHERE `email` = :email;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteUsersDetailsByUserID(Users user) {
        SqlParameterSource param = buildUsersDetailsParameter(user);

        final String query = "DELETE FROM `users_details` WHERE `user_id` = :user_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteStudentsDetailsByStudentID(Student student) {
        SqlParameterSource param = buildStudentsDetailsParameter(student);

        final String query = "DELETE FROM `students_details` WHERE `student_id` = :student_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteInstructorsDetailsByInstructorID(Instructor instructor) {
        SqlParameterSource param = buildInstructorsDetailsParameter(instructor);

        final String query = "DELETE FROM `instructors_details` WHERE `instructors_id` = :instructor_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteUsersRolesByUserIDAndRoleID(AccountsRoles accountsRoles) {
        SqlParameterSource param = buildUsersRolesParameter(accountsRoles);

        final String query = "DELETE FROM `users_roles` WHERE `user_id` = :user_id AND `role_id` = :role_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteRoleByRoleID(AccountsRoles accountsRoles) {
        SqlParameterSource param = buildRolesParameter(accountsRoles);

        final String query = "DELETE FROM `roles` WHERE `role_id` = :role_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int deleteUsersProfilePictureByUserID(Accounts account) {
        SqlParameterSource param = buildUserProfilePicture(account);

        final String query = "DELETE FROM `users_profile_picture` WHERE `user_id` = :user_id;";

        return this.jdbcNamedTemplate.update(query, param);
    }
}
