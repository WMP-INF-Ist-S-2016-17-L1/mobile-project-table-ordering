package com.table.order.restaurateur.service;


import com.table.order.common.exceptions.VenueException;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.common.service.UserService;
import com.table.order.foursquare.FoursquareService;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurateurServiceTest {

    private RestaurateurService restaurateurService;
    private FoursquareService foursquareService;
    private UserService userService;

    Set<FoundVenue> expectedVenuesForSearch = new HashSet<>();

    @BeforeEach
    void setUp() {
        foursquareService = mock(FoursquareService.class);
        userService = mock(UserService.class);
        restaurateurService = new RestaurateurService();
        restaurateurService.setFoursquareService(foursquareService);
//        restaurateurService.setUserService(userService);

        expectedVenuesForSearch = new HashSet<>();
        expectedVenuesForSearch.add(new FoundVenue("1", "McDonalds", null));
        expectedVenuesForSearch.add(new FoundVenue("2", "McDonalds", null));
    }
//
//    @Test
//    public void assignRestaurant_idNullOrEmpty_throwException() {
//        assertThrows(IncorrectRestaurantDataException.class, () ->
//            restaurateurService.assignRestaurantAndGetItsDetails("")
//        );
//
//        assertThrows(IncorrectRestaurantDataException.class, () ->
//                restaurateurService.assignRestaurantAndGetItsDetails(null)
//        );
//    }
//
//    @Test
//    public void assignRestaurant_incorrectLoggedUser_throwUnauthorizedException() throws UnauthorizedException {
//        String venueId = "venueId";
//        doThrow(UnauthorizedException.class)
//                .when(userService)
//                .assignRestaurantToLoggedUser(venueId);
//
//        assertThrows(UnauthorizedException.class, () ->
//                restaurateurService.assignRestaurantAndGetItsDetails(venueId)
//        );
//    }
//
//    @Test
//    public void assignRestaurant_correctVenueIdLoggedRestaurateur_assignToUserAndGetRestaurantDetails() throws UnauthorizedException {
//        String venueId = "venueId";
//        VenueDetails venueDetails = new VenueDetails();
//
//        when(foursquareService.getVenueDetails(venueId)).thenReturn(venueDetails);
//
//        VenueDetails restaurantDetails
//                = restaurateurService.assignRestaurantAndGetItsDetails(venueId);
//
//        verify(userService).assignRestaurantToLoggedUser(venueId);
//        assertTrue(venueDetails == restaurantDetails);
//    }

    @Test
    public void searchForRestaurant_correctData_returnFoundRestaurants() throws VenueException {
        String restaurant = "McDonalds";
        String city = "Rzeszów";

        when(foursquareService.searchForFoodVenues(restaurant, city))
                .thenReturn(expectedVenuesForSearch);

        Set<FoundVenue> restaurants
                = restaurateurService.searchForRestaurant(restaurant, city);

        assertTrue(restaurants == expectedVenuesForSearch);
    }

    @Test
    public void searchForRestaurant_emptyRestaurantName_throwIllegalArgumentException() {
        assertThrows(IncorrectRestaurantDataException.class, () ->
                restaurateurService.searchForRestaurant("", "city")
        );
    }

    @Test
    public void searchForRestaurant_emptyCityName_throwIllegalArgumentException() {
        assertThrows(IncorrectRestaurantDataException.class, () ->
                restaurateurService.searchForRestaurant("res", "")
        );
    }

}