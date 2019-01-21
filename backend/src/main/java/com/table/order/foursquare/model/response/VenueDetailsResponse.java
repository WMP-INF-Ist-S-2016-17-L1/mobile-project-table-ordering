package com.table.order.foursquare.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.table.order.foursquare.model.VenueDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class VenueDetailsResponse {

	@JsonProperty(value = "response")
	private DetailsResponse response;

	public VenueDetails getVenueDetails() {
		if (response != null) {
			return response.getVenueDetails();
		}
		return null;
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class DetailsResponse implements Serializable {

	private static final long serialVersionUID = 812589584662864950L;
	
	@JsonProperty(value = "venue")
	private VenueDetails venueDetails;
}