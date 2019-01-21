package com.table.order.common.service;

import com.table.order.common.model.Notification;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.repository.NotificationRepository;
import com.table.order.restaurateur.model.ActivatedRestaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Slf4j
public class NotificationService {

    private NotificationRepository notificationRepository;

    public void sendReservationRecived(ActivatedRestaurant restaurant) {
        log.error(getClass().getSimpleName() + "sendReservationReceived not implemented yey.");
    }

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void sendForClient(ReservationRequest reservationRequest) {
        Notification notification =
                createNotificationForClient(reservationRequest);

        this.notificationRepository.save(notification);
    }

    @Transactional
    public void sendForRestaurateur(ReservationRequest reservationRequest) {
        Notification notification =
                createNotificationForRestaurateur(reservationRequest);

        this.notificationRepository.save(notification);
    }

    private Notification createNotificationForRestaurateur(ReservationRequest reservationRequest) {
        return new Notification(
                null,
                reservationRequest.getRestaurant().getOwner(),
                reservationRequest,
                "drop_me_plx",
                "never_mind",
                new Date(),
                false
        );
    }

    private Notification createNotificationForClient(ReservationRequest reservationRequest) {
        return new Notification(
                null,
                reservationRequest.getClient(),
                reservationRequest,
                "drop_me_plx",
                reservationRequest.getRestaurateurMessage(),
                new Date(),
                false
        );
    }


    public Page<Notification> findByCurrentClientUsername(String username, Pageable pageable) {
        return this.notificationRepository.findAllByReceivedUserUsername(username, pageable);
    }
}
