package com.louay.model.dao.account;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.users.*;

public interface AccountDao extends GenericDao<Accounts>, CommonDao<Accounts> {
    Student findStudentJoinCourseMemberByStudentId(Student student);
}
