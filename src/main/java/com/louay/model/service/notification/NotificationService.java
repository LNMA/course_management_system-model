package com.louay.model.service.notification;

import com.louay.model.entity.notification.FeedbackNotification;
import com.louay.model.entity.notification.MaterialNotification;
import com.louay.model.entity.notification.UserNotification;

import java.util.Set;

public interface NotificationService {
    UserNotification createUserNotification(UserNotification userNotification);

    UserNotification deleteUserNotificationByNotificationId(UserNotification userNotification);

    UserNotification updateUserNotification(UserNotification userNotification);

    UserNotification findUserNotificationByNotificationId(UserNotification userNotification);

    FeedbackNotification createFeedbackNotification(FeedbackNotification feedbackNotification);

    FeedbackNotification deleteFeedbackNotificationByNotificationId(FeedbackNotification feedbackNotification);

    FeedbackNotification updateFeedbackNotification(FeedbackNotification feedbackNotification);

    FeedbackNotification findFeedbackNotificationByNotificationId(FeedbackNotification feedbackNotification);

    MaterialNotification createMaterialNotification(MaterialNotification materialNotification);

    MaterialNotification deleteMaterialNotificationByNotificationId(MaterialNotification materialNotification);

    MaterialNotification updateMaterialNotification(MaterialNotification materialNotification);

    MaterialNotification findMaterialNotificationByNotificationId(MaterialNotification materialNotification);

    Integer getCountNotSeenUserNotificationByUserId(UserNotification userNotification);

    Integer getCountNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);

    Integer getCountNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<MaterialNotification> findNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<FeedbackNotification> findNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);
}
