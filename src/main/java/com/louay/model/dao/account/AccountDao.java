package com.louay.model.dao.account;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.users.*;
import com.louay.model.entity.wrapper.GeneralSearch;

import java.util.Set;

public interface AccountDao extends GenericDao<Accounts>, CommonDao<Accounts> {
    Student findStudentJoinCourseMemberByStudentId(Student student);

    Set<Users> findUserLikePagination(GeneralSearch generalSearch);

    Long getCountUserLikePagination(GeneralSearch generalSearch);
}
