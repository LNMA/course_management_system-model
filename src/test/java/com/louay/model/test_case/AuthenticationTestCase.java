package com.louay.model.test_case;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.authentication.CookieLogin;
import com.louay.model.entity.users.Admin;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.authentication.AuthenticationService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthenticationTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    @Autowired
    private AuthenticationService authenticationService;

    @Before
    public void initialize01_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Test
    public void testCase01_create_account() {
        Admin admin = new Admin();
        admin.setEmail("authentication@test.com");
        admin.setPassword("1234");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.createAccount(admin);

        System.out.println(admin);
    }

    @Test
    public void testCase02_create_cookieLogin() {
        Admin admin = new Admin();
        admin.setEmail("authentication@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);

        CookieLogin cookieLogin = this.applicationContext.getBean(CookieLogin.class);
        cookieLogin.setAdmin(admin);
        cookieLogin.setSecureCode(123);
        this.authenticationService.createCookieLogin(cookieLogin);

        System.out.println(cookieLogin);
    }

    @Test
    public void testCase03_find_and_update_cookieLogin() {
        Admin admin = new Admin();
        admin.setEmail("authentication@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);

        CookieLogin cookieLogin = this.applicationContext.getBean(CookieLogin.class);
        cookieLogin.setAdmin(admin);
        this.authenticationService.findCookieLoginByEmail(cookieLogin);
        cookieLogin.setSecureCode(321);

        this.authenticationService.updateCookieLogin(cookieLogin);

        System.out.println(cookieLogin);
    }

    @Test
    public void testCase04_delete_cookieLogin() {
        Admin admin = new Admin();
        admin.setEmail("authentication@test.com");

        CookieLogin cookieLogin = this.applicationContext.getBean(CookieLogin.class);
        cookieLogin.setAdmin(admin);
        this.authenticationService.deleteCookieLoginByEmail(cookieLogin);
    }

    @Test
    public void testCase05_find_and_delete_account() {
        Admin admin = new Admin();
        admin.setEmail("authentication@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        accountService.deleteAccountByEmail(admin);
    }


}
