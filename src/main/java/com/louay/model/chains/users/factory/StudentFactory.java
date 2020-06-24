package com.louay.model.chains.users.factory;

import com.louay.model.chains.users.Student;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StudentFactory extends AbstractFactoryUser{

    public StudentFactory(Student users) {
        super(users);
    }
}
