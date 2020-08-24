package com.louay.model.entity.wrapper;

import com.louay.model.entity.users.Student;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StudentHomeWrapper implements Serializable, Comparable<StudentHomeWrapper> {
    private static final long serialVersionUID = -1969373294226748539L;
    private Student student;
    private StringBuilder pictureBase64;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StringBuilder getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(StringBuilder pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }

    public String getTimeBirthday(){
        return this.student.getBirthday().getTime().toString();
    }

    public String getTimeJoinDate(){
        return this.student.getAdmin().getJoinDate().getTime().toString();
    }

    @Override
    public int compareTo(StudentHomeWrapper o) {
        return this.student.compareTo(o.getStudent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentHomeWrapper that = (StudentHomeWrapper) o;
        return getStudent().equals(that.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent());
    }

    @Override
    public String toString() {
        return "StudentHomeWrapper{" +
                "student=" + student +
                ", pictureBase64=" + pictureBase64 +
                '}';
    }
}
