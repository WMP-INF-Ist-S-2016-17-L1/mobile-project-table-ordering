package com.table.order.common.model.projection;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.restaurateur.model.ActivatedRestaurant;

@Projection(name = "withUsername", types = ReservationRequest.class)
public interface ReservationRequestWithClientName {
	int getId();

	ActivatedRestaurant getRestaurant();

	int getNumberOfPersons();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date getReservationDateTime();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date getCreatedDate();
	
	String getClientMessage();
	String getRestaurateurMessage();

	ReservationRequestStatus getStatus();
	
	@Value("#{target.client.username}")
	String getClientUsername();

	boolean isActive();
}
