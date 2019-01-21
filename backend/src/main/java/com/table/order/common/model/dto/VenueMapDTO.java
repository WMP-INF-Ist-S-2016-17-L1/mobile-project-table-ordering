package com.table.order.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueMapDTO {
    private String id;
    private String name;
    private VenueLocationDTO location;
    private Boolean isRegistered;
}
