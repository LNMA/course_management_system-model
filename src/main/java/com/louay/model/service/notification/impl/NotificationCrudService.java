package com.louay.model.service.notification.impl;

import com.louay.model.dao.notification.UserNotificationDao;
import com.louay.model.entity.notification.FeedbackNotification;
import com.louay.model.entity.notification.MaterialNotification;
import com.louay.model.entity.notification.UserNotification;
import com.louay.model.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Set;

@Service
public class NotificationCrudService implements NotificationService, Serializable {
    private static final long serialVersionUID = 2854965535778062593L;
    private final UserNotificationDao userNotificationDao;

    @Autowired
    public NotificationCrudService(UserNotificationDao userNotificationDao) {
        Assert.notNull(userNotificationDao, "userNotificationDao cannot be null!.");

        this.userNotificationDao = userNotificationDao;
    }

    private UserNotificationDao getUserNotificationDao() {
        return userNotificationDao;
    }


    @Transactional
    @Override
    public UserNotification createUserNotification(UserNotification userNotification) {
        return getUserNotificationDao().save(userNotification);
    }

    @Transactional
    @Override
    public UserNotification deleteUserNotificationByNotificationId(UserNotification userNotification) {
        return getUserNotificationDao().delete(userNotification);
    }

    @Transactional
    @Override
    public UserNotification updateUserNotification(UserNotification userNotification) {
        return getUserNotificationDao().update(userNotification);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public UserNotification findUserNotificationByNotificationId(UserNotification userNotification) {
        return getUserNotificationDao().findOneById(userNotification);
    }

    @Transactional
    @Override
    public FeedbackNotification createFeedbackNotification(FeedbackNotification feedbackNotification) {
        return getUserNotificationDao().save(feedbackNotification);
    }

    @Transactional
    @Override
    public FeedbackNotification deleteFeedbackNotificationByNotificationId(FeedbackNotification feedbackNotification) {
        return getUserNotificationDao().delete(feedbackNotification);
    }

    @Transactional
    @Override
    public FeedbackNotification updateFeedbackNotification(FeedbackNotification feedbackNotification) {
        return getUserNotificationDao().update(feedbackNotification);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public FeedbackNotification findFeedbackNotificationByNotificationId(FeedbackNotification feedbackNotification) {
        return getUserNotificationDao().findOneById(feedbackNotification);
    }

    @Transactional
    @Override
    public MaterialNotification createMaterialNotification(MaterialNotification materialNotification) {
        return getUserNotificationDao().save(materialNotification);
    }

    @Transactional
    @Override
    public MaterialNotification deleteMaterialNotificationByNotificationId(MaterialNotification materialNotification) {
        return getUserNotificationDao().delete(materialNotification);
    }

    @Transactional
    @Override
    public MaterialNotification updateMaterialNotification(MaterialNotification materialNotification) {
        return getUserNotificationDao().update(materialNotification);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public MaterialNotification findMaterialNotificationByNotificationId(MaterialNotification materialNotification) {
        return getUserNotificationDao().findOneById(materialNotification);
    }

    @Transactional
    @Override
    public Integer getCountNotSeenUserNotificationByUserId(UserNotification userNotification) {
        return getUserNotificationDao().getCountNotSeenUserNotificationByUserId(userNotification);
    }

    @Transactional
    @Override
    public Integer getCountNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification) {
        return getUserNotificationDao().getCountNotSeenFeedbackNotificationByUserId(feedbackNotification);
    }

    @Transactional
    @Override
    public Integer getCountNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification) {
        return getUserNotificationDao().getCountNotSeenMaterialNotificationByUserId(materialNotification);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Set<MaterialNotification> findNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification) {
        return getUserNotificationDao().findNotSeenMaterialNotificationByUserId(materialNotification);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Set<FeedbackNotification> findNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification) {
        return getUserNotificationDao().findNotSeenFeedbackNotificationByUserId(feedbackNotification);
    }
}
