package com.table.order.common.place.finder.model.froursquare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("Location")
public class LocationDTO {

    @JsonProperty("address")
    private String address;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;


}
