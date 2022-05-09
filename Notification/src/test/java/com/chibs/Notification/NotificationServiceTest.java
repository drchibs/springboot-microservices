package com.chibs.Notification;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationService.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceTest {
    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    /**
     * Method under test: {@link NotificationService#sendNotification(com.chibs.clients.notification.NotificationDTO)}
     */
    @Test
    void testSendNotification() {
        Notification notification = new Notification();
        notification.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        notification.setId(123L);
        notification.setMessage("Not all who wander are lost");
        notification.setTitle("Dr");
        notification.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.notificationRepository.save((Notification) any())).thenReturn(notification);

        Notification notification1 = new Notification();
        notification1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        notification1.setId(123L);
        notification1.setMessage("Not all who wander are lost");
        notification1.setTitle("Dr");
        notification1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        assertSame(notification, this.notificationService.sendNotification(notification1));
        verify(this.notificationRepository).save((Notification) any());
    }

    /**
     * Method under test: {@link NotificationService#getNotification(Long)}
     */
    @Test
    void testGetNotification() {
        Notification notification = new Notification();
        notification.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        notification.setId(123L);
        notification.setMessage("Not all who wander are lost");
        notification.setTitle("Dr");
        notification.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Notification> ofResult = Optional.of(notification);
        when(this.notificationRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(notification, this.notificationService.getNotification(123L));
        verify(this.notificationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link NotificationService#getAllNotifications()}
     */
    @Test
    void testGetAllNotifications() {
        ArrayList<Notification> notificationList = new ArrayList<>();
        when(this.notificationRepository.findAll()).thenReturn(notificationList);
        Iterable<Notification> actualAllNotifications = this.notificationService.getAllNotifications();
        assertSame(notificationList, actualAllNotifications);
        assertTrue(((Collection<Notification>) actualAllNotifications).isEmpty());
        verify(this.notificationRepository).findAll();
    }

    /**
     * Method under test: {@link NotificationService#deleteNotification(Long)}
     */
    @Test
    void testDeleteNotification() {
        doNothing().when(this.notificationRepository).deleteById((Long) any());
        this.notificationService.deleteNotification(123L);
        verify(this.notificationRepository).deleteById((Long) any());
    }
}

