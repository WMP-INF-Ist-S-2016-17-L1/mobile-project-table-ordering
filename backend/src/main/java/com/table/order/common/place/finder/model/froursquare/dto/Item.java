package com.table.order.common.place.finder.model.froursquare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @JsonProperty("venue")
    private VenueDTO venue;

}
