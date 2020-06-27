package com.louay.model.chains.status;

import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class SignInStatus extends AccountStatus {
    private Long UserSignInID;
    private java.sql.Timestamp signInDate;

    @Autowired
    public SignInStatus(UserFactoryProducer userFactoryProducer) {
        super(userFactoryProducer);
    }

    public Long getUserSignInID() {
        return UserSignInID;
    }

    public void setUserSignInID(Long userSignInID) {
        UserSignInID = userSignInID;
    }

    public Timestamp getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Timestamp signInDate) {
        this.signInDate = signInDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignInStatus)) return false;
        if (!super.equals(o)) return false;
        SignInStatus that = (SignInStatus) o;
        return getUserSignInID().equals(that.getUserSignInID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserSignInID());
    }

    @Override
    public String toString() {
        return super.toString() + ", SignInStatus{" +
                "UserSignInID=" + UserSignInID +
                ", signInDate=" + signInDate +
                '}';
    }
}
