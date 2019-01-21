package com.table.order.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueLocationDTO {
    private String address;
    private BigDecimal lat;
    private BigDecimal lng;
    private String state;
    private String city;
    private String country;
}
