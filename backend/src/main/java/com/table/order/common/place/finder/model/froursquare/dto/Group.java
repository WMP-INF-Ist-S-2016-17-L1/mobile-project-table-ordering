package com.table.order.common.place.finder.model.froursquare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @JsonProperty("items")
    private List<Item> items = null;


}
