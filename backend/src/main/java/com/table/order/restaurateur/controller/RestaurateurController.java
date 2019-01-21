package com.table.order.restaurateur.controller;

import java.util.List;
import java.util.Set;

import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.model.dto.NotificationDTO;
import com.table.order.common.model.dto.ReservationDTO;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.restaurateur.model.dto.AcceptRejectReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.table.order.common.exceptions.VenueException;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.restaurateur.model.Restaurant;
import com.table.order.restaurateur.service.RestaurateurService;

@RepositoryRestController
@RequestMapping("/api/restaurants")
public class RestaurateurController {

    private RestaurateurService restaurateurService;

    @Autowired
    public RestaurateurController(RestaurateurService restaurateurService) {
        this.restaurateurService = restaurateurService;
    }

    @PreAuthorize("hasRole('ROLE_RESTAURATEUR')")
    @GetMapping("/notifications")
    public ResponseEntity<Page<NotificationDTO>> getNotifications(Pageable pageable) {
        return ResponseEntity.ok(this.restaurateurService.getNotifications(pageable));
    }

    @PreAuthorize("hasRole('ROLE_RESTAURATEUR')")
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> getReservations(
            @RequestParam("restaurantId") Integer restaurantId,
            @RequestParam("status") ReservationRequestStatus status) {
        return ResponseEntity.ok(
                restaurateurService.getReservationsByRestaurantId(
                        restaurantId,
                        status
                )
        );
    }

    @PreAuthorize("hasRole('ROLE_RESTAURATEUR')")
    @PutMapping("/assign")
    public ResponseEntity<Restaurant> assignRestaurantToRestauretur(
    		@RequestParam("foursquare_id") String foursquareRestaurantId) throws VenueException {

    	Restaurant restaurant = restaurateurService.saveRestaurantWithCurrentUser(foursquareRestaurantId);
    	return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_RESTAURATEUR')")
    @GetMapping("/search")
    public ResponseEntity<Set<FoundVenue>> searchRestaurants(
            @RequestParam("restaurant_name") String restaurantName,
            @RequestParam("city") String city) throws VenueException {

        Set<FoundVenue> foundVenues
                = restaurateurService.searchForRestaurant(restaurantName, city);

        return ResponseEntity.ok(foundVenues);
    }

    @PreAuthorize("hasAuthority('ROLE_RESTAURATEUR')")
    @PutMapping("/reservations/accept")
    public ResponseEntity<?> acceptReservation(@RequestBody AcceptRejectReservationDTO reservationData) {
        try {
            restaurateurService.acceptReservation(reservationData);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_RESTAURATEUR')")
    @PutMapping("/reservations/reject")
    public ResponseEntity<?> rejectReservation(@RequestBody AcceptRejectReservationDTO reservationData) {
        try {
            restaurateurService.rejectReservation(reservationData);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

}
