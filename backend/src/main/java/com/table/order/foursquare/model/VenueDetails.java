package com.table.order.foursquare.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VenueDetails extends FoundVenue {

	private static final long serialVersionUID = -6346740747138911459L;
	
	private String websiteUrl;
    private BigDecimal rating;
//
//    private VenueDetails(FoundVenue foundVenue) {
//        super(foundVenue.getFoursquareId(), foundVenue.getName(), foundVenue.getLocation());
//    }
//
//    public static VenueDetails createVenuesDetails(FoundVenue foundVenue,
//                                                   String websiteUrl,
//                                                   BigDecimal rating) {
//        VenueDetails venueDetails = new VenueDetails(foundVenue);
//        venueDetails.setWebsiteUrl(websiteUrl);
//        venueDetails.setRating(rating);
//        return venueDetails;
//    }
}
