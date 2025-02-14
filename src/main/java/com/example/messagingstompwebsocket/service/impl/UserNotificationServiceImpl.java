package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.model.UserNotification;
import com.example.messagingstompwebsocket.model.User;
import com.example.messagingstompwebsocket.repository.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserNotificationService {

    @Autowired
    private UserNotificationRepository repository;

    public UserNotification saveNotification(UserNotification notification) {
        notification.setTimestamp(LocalDateTime.now());
        return repository.save(notification);
    }

    public List<UserNotification> getNotificationsForUser(User user) {
        return repository.findByUser(user);
    }

    public List<UserNotification> getNotificationsForUser(User user, Pageable pageable) {
        return repository.findByUser(user, pageable);
    }

    public long countNotificationsForUser(User user) {
        return repository.countByUser(user);
    }

    // Lấy thông báo theo loại hành động
    public List<UserNotification> getNotificationsByAction(String action) {
        return repository.findByAction(action);
    }

}
