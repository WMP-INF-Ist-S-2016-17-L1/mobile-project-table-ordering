package com.table.order.common.place.finder.controller;

import com.table.order.common.exceptions.FoursquareConnectionErrorException;
import com.table.order.common.model.dto.VenueMapDTO;
import com.table.order.common.place.finder.model.Request;
import com.table.order.common.place.finder.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @PostMapping("/search")
    public ResponseEntity<List<VenueMapDTO>> searchForFoursquare(@RequestBody Request request) {
        try {
            return ResponseEntity.ok(venueService.searchVenues(request));
        } catch (FoursquareConnectionErrorException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }

    @GetMapping("/searchBy")
    public ResponseEntity<List<VenueMapDTO>> searchVenuesByCity(@RequestParam(value = "query", required = false) String query,
                                                                @RequestParam(value = "city") String city) {

        return ResponseEntity.ok(venueService.searchVenuesByCity(query, city));
    }


}
