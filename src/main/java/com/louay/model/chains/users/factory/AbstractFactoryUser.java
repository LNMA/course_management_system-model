package com.louay.model.chains.users.factory;

import com.louay.model.chains.users.Users;

public abstract class AbstractFactoryUser {
    private Users users;

    public AbstractFactoryUser(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }
}
