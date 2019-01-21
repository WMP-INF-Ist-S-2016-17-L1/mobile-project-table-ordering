package com.table.order.common.place.finder.model;

import com.table.order.common.place.finder.model.froursquare.dto.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Location implements Serializable {

    private static final long serialVersionUID = 5326519596817667942L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private BigDecimal lat;

    private BigDecimal lng;

    private String state;

    private String city;

    private String country;

    public static Location createByDto(LocationDTO locationDTO) {
        Location location = new Location();

        BeanUtils.copyProperties(locationDTO, location);
        location.setLat(new BigDecimal(locationDTO.getLat()));
        location.setLng(new BigDecimal(locationDTO.getLng()));

        return location;
    }
}
