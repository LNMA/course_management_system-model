package com.louay.model.chains.status;

import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class AccountStatus extends UserStatus {
    private Boolean isOnline;
    private Boolean isValid;

    @Autowired
    public AccountStatus(UserFactoryProducer userFactoryProducer) {
        super(userFactoryProducer);
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountStatus)) return false;
        AccountStatus that = (AccountStatus) o;
        return Objects.equals(isOnline, that.isOnline) &&
                Objects.equals(isValid, that.isValid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOnline, isValid);
    }

    @Override
    public String toString() {
        return super.toString() + ", AccountStatus{" +
                "isOnline=" + isOnline +
                ", isValid=" + isValid +
                '}';
    }
}
