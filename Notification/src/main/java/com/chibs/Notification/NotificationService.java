package com.chibs.Notification;

import com.chibs.clients.notification.NotificationDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification sendNotification(NotificationDTO notification) {
        Notification newNotification = Notification.builder()
                .title(notification.getTitle())
                .message(notification.getMessage()).build();
        return notificationRepository.save(newNotification);
    }

    public Notification getNotification(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Iterable<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
