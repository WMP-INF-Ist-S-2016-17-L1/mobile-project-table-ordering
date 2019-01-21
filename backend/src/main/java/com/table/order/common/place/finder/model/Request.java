package com.table.order.common.place.finder.model;

import lombok.Data;

@Data
public class Request {

    private String query;
    private Double lat;
    private Double lng;

}
