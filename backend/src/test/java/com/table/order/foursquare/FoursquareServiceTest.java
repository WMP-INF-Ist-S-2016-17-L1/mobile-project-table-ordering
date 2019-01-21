package com.table.order.foursquare;

import com.table.order.common.exceptions.VenueException;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.repository.FoursquareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoursquareServiceTest {

    private FoursquareService foursquareService;
    private FoursquareRepository foursquareRepository;

    @BeforeEach
    void setUp() {
        foursquareRepository = mock(FoursquareRepository.class);
        foursquareService = new FoursquareService(foursquareRepository);
    }

    @Test
    void searchForFoodVenues_correctData_returnDataFromRepository() throws VenueException {
        String restaurant = "restaurant";
        String city = "city";

        HashSet<FoundVenue> expectedVenues = new HashSet<>();
        when(foursquareRepository.findFoodVenuesByNameAndCity(restaurant, city))
                .thenReturn(expectedVenues);

        Set<FoundVenue> foundVenues =
                foursquareService.searchForFoodVenues(restaurant, city);

        assertTrue(expectedVenues == foundVenues);
    }
}