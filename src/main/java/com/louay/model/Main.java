package com.louay.model;

import com.louay.model.chains.users.Accounts;
import com.louay.model.chains.users.Student;
import com.louay.model.dao.accounts.CreateAccountsDAO;
import com.louay.model.util.date.NowDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.louay.model");
        applicationContext.refresh();
        Accounts accounts = applicationContext.getBean(Student.class);
        accounts.setEmail("louay@test.com");
        accounts.setForename("louay");
        accounts.setSurname("amr");
        accounts.setPassword("123");
        accounts.setJoinDate(NowDate.getNowTimestamp());

        CreateAccountsDAO createAccountsDAO = applicationContext.getBean(CreateAccountsDAO.class);
        createAccountsDAO.createUsers(accounts);
    }
}
