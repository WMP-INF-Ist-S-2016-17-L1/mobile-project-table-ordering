package com.table.order.foursquare.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueLocation implements Serializable  {

	private static final long serialVersionUID = 6483202936253427267L;
	
	private String country;
    private String city;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

}
