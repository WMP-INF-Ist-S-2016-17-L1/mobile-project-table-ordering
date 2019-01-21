package com.table.order.foursquare;

import com.table.order.common.exceptions.VenueException;
import com.table.order.common.util.StringUtils;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.foursquare.repository.FoursquareRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FoursquareService {

    private FoursquareRepository foursquareRepository;

    public FoursquareService(FoursquareRepository foursquareRepository) {
        this.foursquareRepository = foursquareRepository;
    }

    public Set<FoundVenue> searchForFoodVenues(String restaurant, String city) throws VenueException {
        return foursquareRepository.findFoodVenuesByNameAndCity(restaurant, city);
    }

    public VenueDetails getVenueDetails(String venueId) throws VenueException {
        if (StringUtils.isNullOrEmpty(venueId))
            return null;

        return foursquareRepository.getVenueDetails(venueId);
    }
}
