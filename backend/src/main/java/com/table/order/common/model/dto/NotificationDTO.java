package com.table.order.common.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.model.dto.RestaurantDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NotificationDTO {

    private Long id;
    private Boolean displayed;
    @JsonProperty("restaurantMessage") private String message;
    @JsonProperty("notificationDate") private Date dateAndTime;

    private Date reservationDate;
    @JsonProperty("reservationStatus") private ReservationRequestStatus reservationRequestStatus;
    @JsonProperty("reservationId") private Long reservationRequestId;
    private RestaurantDTO restaurant;

}
