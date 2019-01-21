package com.table.order.restaurateur.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AcceptRejectReservationDTO {

    private Long reservationId;
    private String message;

}
