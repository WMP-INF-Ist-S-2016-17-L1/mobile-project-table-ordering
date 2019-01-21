package com.table.order.restaurateur.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectRestaurantDataException extends RuntimeException {

	private static final long serialVersionUID = 6300182708708596567L;
}
