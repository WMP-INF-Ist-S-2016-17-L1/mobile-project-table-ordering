package com.table.order.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class VenueException extends Exception {

	private static final long serialVersionUID = 2654334068004413847L;

	public VenueException(String message) {
		super(message);
	}
}
