package com.table.order.common.model.projection;

import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.table.order.common.model.Notification;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.restaurateur.model.ActivatedRestaurant;

@Projection(name = "withRestaurant", types = ReservationRequest.class)
public interface ReservationRequestWithRestaurant {

	int getId();

	ActivatedRestaurant getRestaurant();

	int getNumberOfPersons();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date getReservationDateTime();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date getCreatedDate();
	
	Set<Notification> getNotifications();
	
	String getClientMessage();
	String getRestaurateurMessage();

	ReservationRequestStatus getStatus();

	boolean isActive();
}
