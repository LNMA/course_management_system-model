package com.louay.model.chains.status;

import com.louay.model.chains.users.Users;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class UserStatus {
    private UserFactoryProducer userFactoryProducer;
    private Users user;

    public UserStatus(UserFactoryProducer userFactoryProducer) {
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

    public void setUserInstance(boolean student){
        this.user = this.userFactoryProducer.getFactory(student).getUsers();
    }

    public UserFactoryProducer getUserFactoryProducer() {
        return userFactoryProducer;
    }

    public void setUserFactoryProducer(UserFactoryProducer userFactoryProducer) {
        this.userFactoryProducer = userFactoryProducer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatus that = (UserStatus) o;
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
