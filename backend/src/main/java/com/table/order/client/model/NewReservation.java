package com.table.order.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewReservation {

    String restaurantApiId;

    int numberOfPersons;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dateAndTime;
    private String clientMessage;
}
