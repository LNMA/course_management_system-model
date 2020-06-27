package com.louay.model.chains.material;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
    public String toString() {
        return super.toString() + ", LinksMaterials{" +
                "link=" + link +
                '}';
    }
}
