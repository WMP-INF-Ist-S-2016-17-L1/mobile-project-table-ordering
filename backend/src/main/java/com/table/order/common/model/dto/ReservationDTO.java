package com.table.order.common.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface ReservationDTO {

    Long getId();
    String getClientUsername();
    Integer getNumberOfPersons();

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @JsonProperty("dateAndTime")
    Date getReservationDateTime();
    String getClienMessage();
    String getRestaurateurMessage();
}
