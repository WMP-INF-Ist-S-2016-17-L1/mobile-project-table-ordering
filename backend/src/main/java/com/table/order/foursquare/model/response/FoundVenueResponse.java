package com.table.order.foursquare.model.response;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import com.table.order.foursquare.model.FoundVenue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoundVenueResponse implements Serializable {

	private static final long serialVersionUID = 3663084140946967088L;
	
	private Response response;
	
	public Set<FoundVenue> getVenues() {
		if (response != null) {
			return response.getVenues();
		}
		return Collections.emptySet();
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Response implements Serializable {
	
	private static final long serialVersionUID = 812589584662864950L;
	private Set<FoundVenue> venues;
}