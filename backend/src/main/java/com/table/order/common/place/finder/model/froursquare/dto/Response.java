package com.table.order.common.place.finder.model.froursquare.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    @JsonProperty("groups")
    private List<Group> groups = null;


}
