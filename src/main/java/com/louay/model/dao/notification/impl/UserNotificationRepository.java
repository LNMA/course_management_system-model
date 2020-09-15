package com.louay.model.dao.notification.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.notification.UserNotificationDao;
import com.louay.model.entity.notification.FeedbackNotification;
import com.louay.model.entity.notification.MaterialNotification;
import com.louay.model.entity.notification.UserNotification;
import com.louay.model.entity.notification.constant.NotificationType;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserNotificationRepository extends CommonDaoImpl<UserNotification> implements UserNotificationDao {
    private static final long serialVersionUID = -4670673171379886963L;

    @Override
    public <S extends UserNotification> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT un From UserNotification un " +
                "WHERE un.notificationId = :notificationId")
                .setParameter("notificationId", entity.getNotificationId())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends UserNotification> S delete(S entity) {
        Class<? extends UserNotification> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getNotificationId());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends UserNotification> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends UserNotification> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getNotificationId());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends UserNotification> S findOneById(S entity) {
        Class<? extends UserNotification> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getNotificationId());
        return result;
    }

    @Override
    public <S extends UserNotification> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UserNotification> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getNotificationId());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public Long getCountNotSeenUserNotificationByUserId(UserNotification userNotification) {
        return getEntityManager().createQuery("SELECT COUNT(un) FROM UserNotification un " +
                "WHERE un.users.email = :email AND un.isSeen = :isSeen", Long.class)
                .setParameter("email", userNotification.getUsers().getEmail())
                .setParameter("isSeen", false)
                .setMaxResults(1)
                .getResultList().get(0);
    }

    @Override
    public Long getCountNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification) {
        return getEntityManager().createQuery("SELECT COUNT(fn) FROM FeedbackNotification fn " +
                "WHERE fn.users.email = :email AND fn.isSeen = :isSeen " +
                "AND fn.notificationType = :notificationType", Long.class)
                .setParameter("email", feedbackNotification.getUsers().getEmail())
                .setParameter("isSeen", false)
                .setParameter("notificationType", NotificationType.FEEDBACK)
                .setMaxResults(1)
                .getResultList().get(0);
    }

    @Override
    public Long getCountNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification) {
        return getEntityManager().createQuery("SELECT COUNT(mn) FROM MaterialNotification mn " +
                "WHERE mn.users.email = :email AND mn.isSeen = :isSeen AND " +
                "mn.notificationType = :notificationType", Long.class)
                .setParameter("email", materialNotification.getUsers().getEmail())
                .setParameter("isSeen", false)
                .setParameter("notificationType", NotificationType.MATERIAL)
                .setMaxResults(1)
                .getResultList().get(0);
    }

    @Override
    public Set<MaterialNotification> findNotSeenMaterialNotificationByUserId(MaterialNotification materialNotification) {
        List<MaterialNotification> notificationList = getEntityManager().createQuery(
                "SELECT mn FROM MaterialNotification mn WHERE " +
                        "mn.users.email = :email AND mn.isSeen = :isSeen " +
                        "AND mn.notificationType = :notificationType", MaterialNotification.class)
                .setParameter("email", materialNotification.getUsers().getEmail())
                .setParameter("isSeen", false)
                .setParameter("notificationType", NotificationType.MATERIAL)
                .getResultList();
        return new HashSet<>(notificationList);
    }

    @Override
    public Set<FeedbackNotification> findNotSeenFeedbackNotificationByUserId(FeedbackNotification feedbackNotification) {
        List<FeedbackNotification> notificationList = getEntityManager().createQuery(
                "SELECT fn FROM FeedbackNotification fn WHERE " +
                        "fn.users.email = :email AND fn.isSeen = :isSeen " +
                        "AND fn.notificationType = :notificationType", FeedbackNotification.class)
                .setParameter("email", feedbackNotification.getUsers().getEmail())
                .setParameter("isSeen", false)
                .setParameter("notificationType", NotificationType.FEEDBACK)
                .getResultList();
        return new HashSet<>(notificationList);
    }
}
