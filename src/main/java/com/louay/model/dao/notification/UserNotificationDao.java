package com.louay.model.dao.notification;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.notification.FeedbackNotification;
import com.louay.model.entity.notification.MaterialNotification;
import com.louay.model.entity.notification.UserNotification;

import java.util.Set;

public interface UserNotificationDao extends GenericDao<UserNotification>, CommonDao<UserNotification> {
    Integer getCountNotSeenUserNotificationByUserId(UserNotification userNotification);

    Integer getCountNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);

    Integer getCountNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<MaterialNotification> findNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<FeedbackNotification> findNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);
}
