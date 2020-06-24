package com.louay.model.chains.users.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserFactoryProducer {
    private StudentFactory studentFactory;
    private InstructorFactory instructorFactory;

    public UserFactoryProducer() {
    }

    public StudentFactory getStudentFactory() {
        return studentFactory;
    }

    @Autowired
    public void setStudentFactory(StudentFactory studentFactory) {
        this.studentFactory = studentFactory;
    }

    public InstructorFactory getInstructorFactory() {
        return instructorFactory;
    }

    @Autowired
    public void setInstructorFactory(InstructorFactory instructorFactory) {
        this.instructorFactory = instructorFactory;
    }

    public AbstractFactoryUser getFactory(boolean student) {
        if (student) {
            return this.getStudentFactory();
        } else {
            return this.getInstructorFactory();
        }
    }
}
