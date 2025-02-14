package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.model.User;
import com.example.messagingstompwebsocket.model.UserNotification;
import com.example.messagingstompwebsocket.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserNotificationService userNotificationService;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);

        try {
            UserNotification notification = new UserNotification("CREATE", savedUser);
            userNotificationService.saveNotification(notification);
            // Log chi tiết để debug
            logger.info("Sending WebSocket notification: {}", notification);
            messagingTemplate.convertAndSend("/topic/users", notification);
        } catch (Exception e) {
            logger.error("Error sending WebSocket message", e);
        }

        return savedUser;
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        User updatedUser = userRepository.save(user);

        // Gửi thông báo WebSocket
        UserNotification notification = new UserNotification("UPDATE", updatedUser);
        userNotificationService.saveNotification(notification);
        messagingTemplate.convertAndSend("/topic/users", notification);

        return updatedUser;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        // Gửi thông báo WebSocket
        UserNotification notification = new UserNotification("DELETE", user);
        userNotificationService.saveNotification(notification);
        messagingTemplate.convertAndSend("/topic/users", notification);
    }
}