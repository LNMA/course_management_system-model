package com.louay.model.service.notification;

import com.louay.model.entity.notification.FeedbackNotification;
import com.louay.model.entity.notification.MaterialNotification;
import com.louay.model.entity.notification.UserNotification;

import java.util.List;
import java.util.Set;

public interface NotificationService {
    UserNotification createUserNotification(UserNotification userNotification);

    List<UserNotification> createUserNotificationFromIterable(Iterable<UserNotification> notificationIterable);

    UserNotification deleteUserNotificationByNotificationId(UserNotification userNotification);

    UserNotification updateUserNotification(UserNotification userNotification);

    UserNotification findUserNotificationByNotificationId(UserNotification userNotification);

    FeedbackNotification createFeedbackNotification(FeedbackNotification feedbackNotification);

    List<FeedbackNotification> createFeedbackNotificationFromIterable(Iterable<FeedbackNotification> notificationIterable);

    FeedbackNotification deleteFeedbackNotificationByNotificationId(FeedbackNotification feedbackNotification);

    FeedbackNotification updateFeedbackNotification(FeedbackNotification feedbackNotification);

    FeedbackNotification findFeedbackNotificationByNotificationId(FeedbackNotification feedbackNotification);

    MaterialNotification createMaterialNotification(MaterialNotification materialNotification);

    List<MaterialNotification> createMaterialNotificationFromIterable(Iterable<MaterialNotification> notificationIterable);

    MaterialNotification deleteMaterialNotificationByNotificationId(MaterialNotification materialNotification);

    MaterialNotification updateMaterialNotification(MaterialNotification materialNotification);

    MaterialNotification findMaterialNotificationByNotificationId(MaterialNotification materialNotification);

    Long getCountNotSeenUserNotificationByUserId(UserNotification userNotification);

    Long getCountNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);

    Long getCountNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<MaterialNotification> findNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification);

    Set<FeedbackNotification> findNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification);
}
