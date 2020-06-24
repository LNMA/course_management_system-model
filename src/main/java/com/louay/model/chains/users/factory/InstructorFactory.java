package com.louay.model.chains.users.factory;

import com.louay.model.chains.users.Instructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InstructorFactory extends AbstractFactoryUser {

    public InstructorFactory(Instructor users) {
        super(users);
    }
}
