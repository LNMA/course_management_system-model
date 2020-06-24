package com.louay.model.chains.material;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class LinksMaterials extends Materials{
    private StringBuilder link;

    @Autowired
    public LinksMaterials(UserFactoryProducer userFactoryProducer, Courses course) {
        super(userFactoryProducer, course);
    }

    public StringBuilder getLink() {
        return link;
    }

    public void setLink(StringBuilder link) {
        this.link = link;
    }

    public void setLinkAuxiliary(String link) {
        this.link = new StringBuilder(link);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinksMaterials)) return false;
        if (!super.equals(o)) return false;
        LinksMaterials that = (LinksMaterials) o;
        return getLink().toString().compareTo(that.getLink().toString()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLink());
    }

    @Override
    public String toString() {
        return super.toString() + ", LinksMaterials{" +
                "link=" + link +
                '}';
    }
}
