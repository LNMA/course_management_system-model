package com.louay.model.dao.notification;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.notification.FeedbackNotification;
import com.louay.model.entity.notification.MaterialNotification;
import com.louay.model.entity.notification.UserNotification;

import java.util.Set;

public interface UserNotificationDao extends GenericDao<UserNotification>, CommonDao<UserNotification> {
    Long getCountNotSeenUserNotificationByUserId(UserNotification userNotification);

    Long getCountNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);

    Long getCountNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<MaterialNotification> findNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<FeedbackNotification> findNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);

    Set<MaterialNotification> findNotSeenMaterialNotificationByUserIdAndCourseId(MaterialNotification materialNotification);

    Set<FeedbackNotification> findNotSeenFeedbackNotificationByUserIdAndCourseId(FeedbackNotification feedbackNotification);
}
