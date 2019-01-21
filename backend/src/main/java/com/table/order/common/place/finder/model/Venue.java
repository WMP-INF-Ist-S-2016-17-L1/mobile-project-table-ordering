package com.table.order.common.place.finder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.table.order.common.place.finder.model.froursquare.dto.VenueDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Venue implements Serializable {

    private static final long serialVersionUID = 3861087540416170354L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @JsonProperty("id")
    private String apiId;

    @JsonProperty("name")
    private String name;

    private int inSearchResults;


    @JsonProperty("location")
    private Location location;


    public static Venue createByDto(VenueDTO venueDTO) {
        Venue venue = new Venue();

        venue.setName(venueDTO.getName());
        venue.setApiId(venueDTO.getId());
        venue.setInSearchResults(1);

        return venue;
    }
}
