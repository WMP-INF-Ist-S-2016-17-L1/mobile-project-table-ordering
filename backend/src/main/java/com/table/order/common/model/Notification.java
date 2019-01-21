package com.table.order.common.model;

import com.table.order.common.model.dto.NotificationDTO;
import com.table.order.common.model.dto.RestaurantDTO;
import com.table.order.common.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "received_user_id")
    private User receivedUser;

    @ManyToOne
    @JoinColumn(name = "reservation_request_id")
    private ReservationRequest reservation;

    private String title;
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dateAndTime;

    private boolean displayed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public static NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new ModelMapper().map(notification, NotificationDTO.class);
        dto.setReservationRequestId(notification.getReservation().getId());
        dto.setReservationRequestStatus(notification.getReservation().getStatus());
        dto.setReservationDate(notification.getReservation().getReservationDateTime());

        RestaurantDTO restaurantDTO =
                new ModelMapper().map(notification.getReservation().getRestaurant(), RestaurantDTO.class);

        dto.setRestaurant(restaurantDTO);

        return dto;
    }
}
