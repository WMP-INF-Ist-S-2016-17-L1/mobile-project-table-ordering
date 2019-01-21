package com.table.order.foursquare.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResponseWrapper {

    @JsonProperty("response")
    private FoundVenueResponse foundVenueResponse;
}
