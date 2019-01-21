package com.table.order.common.service;

import com.table.order.client.model.NewReservation;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.repository.ReservationRequestRepository;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.common.security.model.User;
import com.table.order.restaurateur.model.ActivatedRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationRequestRepository reservationRequestRepository;
    private NotificationService notificationService;

    public ReservationRequest saveNew(NewReservation newReservation, User client, ActivatedRestaurant restaurant) {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setClient(client);
        reservationRequest.setRestaurant(restaurant);
        reservationRequest.setReservationDateTime(newReservation.getDateAndTime());
        reservationRequest.setNumberOfPersons(newReservation.getNumberOfPersons());
        reservationRequest.setStatus(ReservationRequestStatus.SEND);
        reservationRequest.setClientMessage(validateMessage(newReservation.getClientMessage()));

        ReservationRequest savedReservation = reservationRequestRepository.save(reservationRequest);

        notificationService.sendReservationRecived(restaurant);
        return savedReservation;
    }

    public ReservationRequest changeStateOfReservation(Long id, ReservationRequestStatus status, String message) throws UnauthorizedException {
        ReservationRequest reservationRequest = reservationRequestRepository.getOne(id);
        reservationRequest.setStatus(status);
        reservationRequest.setRestaurateurMessage(message);

        notificationService.sendForClient(reservationRequest);

        return reservationRequestRepository.save(reservationRequest);
    }

    private String validateMessage(String message) {
        return message == null || message.trim().isEmpty() ? null : message.trim();
    }

    @Autowired
    public void setReservationRequestRepository(ReservationRequestRepository reservationRequestRepository) {
        this.reservationRequestRepository = reservationRequestRepository;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
