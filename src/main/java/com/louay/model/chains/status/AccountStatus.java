package com.louay.model.chains.status;

import com.louay.model.chains.users.Users;
import com.louay.model.chains.users.constant.UserRole;
import com.louay.model.chains.users.factory.UserFactoryProducer;

import java.util.Objects;

public abstract class AccountStatus {
    private final UserFactoryProducer userFactoryProducer;
    private Users user;

    public AccountStatus(UserFactoryProducer userFactoryProducer) {
        this.userFactoryProducer = userFactoryProducer;
    }

    public Users getUser() {
        if (this.user == null){
            throw new RuntimeException("User must be initialized by using setUserInstance() method.");
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUserInstance(UserRole userRole){
        this.user = this.userFactoryProducer.getFactory(userRole).getUsers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatus that = (AccountStatus) o;
        return getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "user=" + user +
                '}';
    }
}
