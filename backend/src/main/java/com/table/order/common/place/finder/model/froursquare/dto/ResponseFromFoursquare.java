package com.table.order.common.place.finder.model.froursquare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFromFoursquare {

    @JsonProperty("response")
    private Response response;

}
