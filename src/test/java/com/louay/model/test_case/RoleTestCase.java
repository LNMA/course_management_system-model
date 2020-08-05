package com.louay.model.test_case;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.constant.Role;
import com.louay.model.entity.role.AccountsRoles;
import com.louay.model.entity.role.UsersRoles;
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
        admin.setEmail("role@test.com");
        admin.setPassword("1234");

        AccountService service = this.applicationContext.getBean(AccountService.class);
        service.createAccount(admin);
    }

    @Test
    public void testCase02_create_accountRole_and_userRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleName(Role.INSTRUCTOR);

        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("role@test.com");
        AccountService accountService = this.applicationContext.getBean(AccountService.class);
        admin = accountService.findAccountByEmail(admin);

        UsersRoles usersRoles = this.applicationContext.getBean(UsersRoles.class);
        usersRoles.setUsers(admin);
        usersRoles.setAccountsRoles(accountsRoles);

        this.roleService.createUsersRoles(usersRoles);

        System.out.println(usersRoles);
    }

    @Test
    public void testCase03_find_and_update_accountRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleID((long) 1);
        accountsRoles = this.roleService.findAccountRoleByRoleId(accountsRoles);

        System.out.println(accountsRoles);

        this.roleService.updateAccountRole(accountsRoles);
    }

    @Test
    public void testCase04_find_and_update_userRole() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("role@test.com");

        UsersRoles usersRoles = this.applicationContext.getBean(UsersRoles.class);
        usersRoles.setUsers(admin);
        usersRoles = this.roleService.findUsersRolesByUserId(usersRoles);

        System.out.println(usersRoles);

        this.roleService.updateUsersRoles(usersRoles);
    }

    @Test
    public void testCase05_find_and_delete_accountRole_and_userRole() {
        AccountsRoles accountsRoles = this.applicationContext.getBean(AccountsRoles.class);
        accountsRoles.setRoleID((long) 1);
        accountsRoles = this.roleService.findAccountRoleByRoleId(accountsRoles);

        System.out.println(accountsRoles);

        this.roleService.deleteAccountRoleByRoleId(accountsRoles);
    }

    @Test
    public void testCase07_find_and_delete_account() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("role@test.com");

        AccountService accountService = this.applicationContext.getBean(AccountService.class);

        accountService.deleteAccountByEmail(admin);
    }
}
