package com.louay.model.chains.status;

import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@Scope("prototype")
public class CourseJoinStatus extends UserStatus {
    private java.sql.Timestamp joinDate;
    private Boolean isBusy;

    @Autowired
    public CourseJoinStatus(UserFactoryProducer userFactoryProducer) {
        super(userFactoryProducer);
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseJoinStatus)) return false;
        if (!super.equals(o)) return false;
        CourseJoinStatus that = (CourseJoinStatus) o;
        return getJoinDate().compareTo(that.getJoinDate()) == 0 &&
                this.getBusy() == that.getBusy();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getJoinDate(), getBusy());
    }

    @Override
    public String toString() {
        return super.toString() + ", CourseJoinStatus{" +
                "joinDate=" + joinDate +
                ", isBusy=" + isBusy +
                '}';
    }
}
