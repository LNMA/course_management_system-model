package com.louay.model;

import com.louay.model.config.MySpringBootJDBCApplication;
import com.louay.model.entity.users.Admin;
import com.louay.model.entity.users.picute.AccountPicture;
import com.louay.model.service.userpic.AccountPictureService;
import com.louay.model.util.file.FileProcess;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootJDBCApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfilePicTestCase {
    private AnnotationConfigApplicationContext applicationContext;
    private AccountPictureService pictureService;

    @Before
    public void initialize02_ApplicationContext() {
        this.applicationContext = new AnnotationConfigApplicationContext();
        this.applicationContext.scan("com.louay.model");
        this.applicationContext.refresh();
    }

    @Before
    public void initialize01_AccountPictureService() {
        this.pictureService = this.applicationContext.getBean(AccountPictureService.class);
    }

    @Test
    public void testCase01_create_accountPicture() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("louay@test.com");

        AccountPicture accountPicture = this.applicationContext.getBean(AccountPicture.class);

        FileProcess fileProcess = this.applicationContext.getBean(FileProcess.class);
        byte [] bytes = null;
        try {
            //TODO : change picture path
            bytes = fileProcess.readFile("C:\\Users\\Oday Amr\\Documents\\IdeaProjects\\" +
                    "course_management_system-model\\Louay Amr.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        accountPicture.setPicture(bytes);
        accountPicture.setAdmin(admin);

        this.pictureService.createAccountPicture(accountPicture);
    }

    @Test
    public void testCase02_find_and_update_accountPicture() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("louay@test.com");

        AccountPicture accountPicture = this.applicationContext.getBean(AccountPicture.class);
        accountPicture.setAdmin(admin);

        accountPicture = this.pictureService.findAccountPictureByUserId(accountPicture);
        System.out.println(accountPicture);

        FileProcess fileProcess = this.applicationContext.getBean(FileProcess.class);
        byte [] bytes = null;
        try {
            //TODO : change picture path
            bytes = fileProcess.readFile("C:\\Users\\Oday Amr\\Documents\\IdeaProjects\\" +
                    "course_management_system-model\\Louay Amr_black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        accountPicture.setPicture(bytes);

        this.pictureService.updateAccountPicture(accountPicture);
    }

    @Test
    public void testCase03_find_and_delete_accountPicture() {
        Admin admin = this.applicationContext.getBean(Admin.class);
        admin.setEmail("louay@test.com");

        AccountPicture accountPicture = this.applicationContext.getBean(AccountPicture.class);
        accountPicture.setAdmin(admin);

        accountPicture = this.pictureService.findAccountPictureByUserId(accountPicture);
        System.out.println(accountPicture);

        this.pictureService.deleteAccountPictureByUserId(accountPicture);
    }
}
