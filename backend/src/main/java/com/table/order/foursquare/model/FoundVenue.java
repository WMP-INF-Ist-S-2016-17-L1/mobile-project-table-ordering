package com.table.order.foursquare.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoundVenue implements Serializable {

	private static final long serialVersionUID = 2784100798880939125L;

	@JsonProperty(value = "id")
    private String foursquareId;
	
	@JsonProperty(value = "name")
    private String name;
	
	@JsonProperty(value = "location")
    private VenueLocation location;

	@JsonProperty(value = "id")
	public void setFoursquareId(String id) {
		this.foursquareId = id;
	}

	@JsonProperty(value = "foursquareId")
	public String getFoursquareId() {
		return foursquareId;
	}
}
