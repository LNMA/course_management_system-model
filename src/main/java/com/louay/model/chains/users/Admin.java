package com.louay.model.chains.users;

import com.louay.model.chains.users.constant.UserRole;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Admin extends Accounts {

    public Admin() {
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.ADMIN;
    }
}
