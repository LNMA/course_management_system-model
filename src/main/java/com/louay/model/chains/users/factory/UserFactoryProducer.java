package com.louay.model.chains.users.factory;

import com.louay.model.chains.users.constant.UserRole;
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

    public AbstractFactoryUser getFactory(UserRole userRole) {
        if (UserRole.STUDENT.compareTo(userRole) == 0 ) {
            return this.getStudentFactory();
        } else if (UserRole.INSTRUCTOR.compareTo(userRole) == 0){
            return this.getInstructorFactory();
        }else {
            throw new UnsupportedOperationException("UserRole may be STUDENT or INSTRUCTOR only.");
        }
    }
}
