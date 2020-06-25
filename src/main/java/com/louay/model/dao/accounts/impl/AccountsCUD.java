package com.louay.model.dao.accounts.impl;

import com.louay.model.chains.users.Accounts;
import com.louay.model.chains.users.Instructor;
import com.louay.model.chains.users.Student;
import com.louay.model.chains.users.Users;
import com.louay.model.dao.accounts.CreateAccountsDAO;
import com.louay.model.dao.accounts.DeleteAccountsDAO;
import com.louay.model.dao.accounts.UpdateAccountsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;

@Component
@Scope("prototype")
@ComponentScan(basePackages = {"com.louay.model"})
public class AccountsCUD implements CreateAccountsDAO, DeleteAccountsDAO, UpdateAccountsDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    private ApplicationContext context;

    @Autowired
    public AccountsCUD(DataSource dataSource, @Qualifier("buildAnnotationContextModel") ApplicationContext context) {
        this.dataSource = dataSource;
        this.context = context;
    }

    @Override
    public int createUsers(Accounts account) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("email", account.getEmail(), Types.VARCHAR);
        param.addValue("forename", account.getForename(), Types.VARCHAR);
        param.addValue("surname", account.getSurname(), Types.VARCHAR);
        param.addValue("password", account.getPassword(), Types.VARCHAR);
        param.addValue("join_date", account.getJoinDate(), Types.TIMESTAMP);

        String query = "INSERT INTO `users`(`email`, `forename`, `surname`, `password`, `join_date`) VALUES " +
                "(:email, :forename, :surname, :password, :join_date);";

        NamedParameterJdbcTemplate jdbcNamedTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        return jdbcNamedTemplate.update(query, param);
    }

    @Override
    public int createUsersDetails(Users user) {
        return 0;
    }

    @Override
    public int createStudentsDetails(Student student) {
        return 0;
    }

    @Override
    public int createInstructorsDetails(Instructor instructor) {
        return 0;
    }

    @Override
    public int createUsersRoles(Accounts account) {
        return 0;
    }

    @Override
    public int createRole(Accounts account) {
        return 0;
    }

    @Override
    public int createUsersProfilePicture(Accounts account) {
        return 0;
    }

    @Override
    public int deleteUsers(Accounts account) {
        return 0;
    }

    @Override
    public int deleteUsersDetails(Users user) {
        return 0;
    }

    @Override
    public int deleteStudentsDetails(Student student) {
        return 0;
    }

    @Override
    public int deleteInstructorsDetails(Instructor instructor) {
        return 0;
    }

    @Override
    public int deleteUsersRoles(Accounts account) {
        return 0;
    }

    @Override
    public int deleteRole(Accounts account) {
        return 0;
    }

    @Override
    public int deleteUsersProfilePicture(Accounts account) {
        return 0;
    }

    @Override
    public int updateUsers(Accounts account) {
        return 0;
    }

    @Override
    public int updateUsersDetails(Users user) {
        return 0;
    }

    @Override
    public int updateStudentsDetails(Student student) {
        return 0;
    }

    @Override
    public int updateInstructorsDetails(Instructor instructor) {
        return 0;
    }

    @Override
    public int updateUsersRoles(Accounts account) {
        return 0;
    }

    @Override
    public int updateRole(Accounts account) {
        return 0;
    }

    @Override
    public int updateUsersProfilePicture(Accounts account) {
        return 0;
    }
}
