package com.table.order.restaurateur.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.security.model.User;
import com.table.order.restaurateur.repository.BaseRestaurant;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Restaurant extends BaseRestaurant {
	
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonIgnore
	private User owner;
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Restaurant that = (Restaurant) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
