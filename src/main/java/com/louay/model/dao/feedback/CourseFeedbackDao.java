package com.louay.model.dao.feedback;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.feedback.CourseFeedback;

import java.util.Set;

public interface CourseFeedbackDao extends GenericDao<CourseFeedback>, CommonDao<CourseFeedback> {
    Set<CourseFeedback> findCourseFeedbackByCourseId(CourseFeedback courseFeedback);

    Set<CourseFeedback> findCourseFeedbackAndCommentByCourseId(CourseFeedback courseFeedback);
}
