package com.table.order.foursquare.repository;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.table.order.common.exceptions.VenueException;
import com.table.order.common.place.finder.service.FoursquareApiComponent;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.foursquare.model.response.FoundVenueResponse;
import com.table.order.foursquare.model.response.VenueDetailsResponse;

@Component
public class FoursquareRepository {

	private FoursquareApiComponent foursquareApiComponent;

	public FoursquareRepository(FoursquareApiComponent foursquareApiComponent) {
		this.foursquareApiComponent = foursquareApiComponent;
	}

	public Set<FoundVenue> findFoodVenuesByNameAndCity(String name, String city) throws VenueException {
		RestTemplate restTemplate = new RestTemplate();

		try {
			return restTemplate.getForObject(
					foursquareApiComponent.getSearchUrlByCategoryFoodFormat(),
					FoundVenueResponse.class, 
					foursquareApiComponent.getSearchUrlDataWithFoodCategory(name, city)
				).getVenues();
		} 
		catch (HttpClientErrorException ex) {
			throw new VenueException(ex.getMessage());
		}
	}

	public VenueDetails getVenueDetails(String venueId) throws VenueException {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(
					foursquareApiComponent.getDetailsUrlFormat(), 
					VenueDetailsResponse.class,
					foursquareApiComponent.getDetailsUrlData(venueId)
				).getVenueDetails();
		} 
		catch (HttpClientErrorException ex) {
			throw new VenueException(ex.getMessage());
		}
	}
}
