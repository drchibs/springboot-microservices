package com.chibs.Notification;

import com.chibs.clients.notification.NotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationDTO notification) {
        log.info("Notification sent");
        return ResponseEntity.ok(notificationService.sendNotification(notification));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotification(@PathVariable Long id) {
        log.info("Get Notification record");
        return ResponseEntity.ok(notificationService.getNotification(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllNotifications() {
        log.info("Get all Notification records");
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
}
