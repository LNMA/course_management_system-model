package com.louay.model.entity.wrapper;

import com.louay.model.entity.courses.Courses;

import java.io.Serializable;
import java.util.Objects;

public class CourseSearch implements Serializable, Comparable<CourseSearch> {
    private static final long serialVersionUID = 2903818104634302383L;
    private String key;
    private Courses courses;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    @Override
    public int compareTo(CourseSearch o) {
        return this.key.compareTo(o.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSearch that = (CourseSearch) o;
        return getKey().equals(that.getKey()) &&
                getCourses().equals(that.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getCourses());
    }

    @Override
    public String toString() {
        return "CourseSearch{" +
                "key='" + key + '\'' +
                ", courses=" + courses +
                '}';
    }
}
