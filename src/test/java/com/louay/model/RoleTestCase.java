package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.constant.Role;
import com.louay.model.entity.users.role.AccountsRoles;
import com.louay.model.entity.users.role.UsersRoles;
import com.louay.model.service.account.AccountService;
import com.louay.model.service.role.RoleService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleTestCase extends TestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private RoleService roleService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_RoleService() {
        this.roleService = this.applicationContext.getBean(RoleService.class);
    }

    @Test
    public void testCase01_create_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("louay@test.com");
        admin.setPassword("1234");

        AccountService service = this.applicationContext.getBean(AccountService.class);
        service.createAccount(admin);
    }

    @Test
    public void testCase02_create_accountRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleName(Role.INSTRUCTOR);


        System.out.println(this.roleService.createAccountRole(accountsRoles));
    }

    @Test
    public void testCase03_find_and_update_accountRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleName(Role.INSTRUCTOR);
        accountsRoles.setRoleID((long) 2);

        accountsRoles = this.roleService.findAccountRoleByRoleId(accountsRoles);
        System.out.println(accountsRoles);

        accountsRoles.setRoleName(Role.STUDENT);
        this.roleService.updateAccountRole(accountsRoles);
    }

    @Test
    public void testCase04_create_accountRole_and_userRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleName(Role.STUDENT);

        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("louay@test.com");

        this.roleService.createAccountRole(accountsRoles);

        UsersRoles usersRoles = this.applicationContext.getBean(UsersRoles.class);
        usersRoles.setUsers(admin);
        usersRoles.setAccountsRoles(accountsRoles);

        this.roleService.createUsersRoles(usersRoles);
    }

    @Test
    public void testCase05_find_and_update_userRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleID((long) 2);

        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("louay@test.com");

        UsersRoles usersRoles = this.applicationContext.getBean(UsersRoles.class);
        usersRoles.setUsers(admin);
        usersRoles.setAccountsRoles(accountsRoles);

        usersRoles = this.roleService.findUsersRolesByUserId(usersRoles);
        System.out.println(usersRoles);

        usersRoles.getAccountsRoles().setRoleID((long) 3);

        this.roleService.updateUsersRoles(usersRoles);
    }

    @Test
    public void testCase06_find_and_delete_userRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);

        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("louay@test.com");

        UsersRoles usersRoles = this.applicationContext.getBean(UsersRoles.class);
        usersRoles.setUsers(admin);
        usersRoles.setAccountsRoles(accountsRoles);

        usersRoles = this.roleService.findUsersRolesByUserId(usersRoles);

        System.out.println(usersRoles);
        this.roleService.deleteUsersRolesByUserId(usersRoles);
    }

    @Test
    public void testCase07_find_and_delete_accountRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleID((long) 2);

        accountsRoles = this.roleService.findAccountRoleByRoleId(accountsRoles);

        System.out.println(accountsRoles);

        this.roleService.deleteAccountRoleByRoleId(accountsRoles);
    }
}
