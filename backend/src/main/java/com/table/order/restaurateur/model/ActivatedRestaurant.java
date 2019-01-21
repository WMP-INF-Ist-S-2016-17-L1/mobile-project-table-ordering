package com.table.order.restaurateur.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.model.Activated;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.security.model.User;
import com.table.order.restaurateur.repository.BaseRestaurant;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@Where(clause = "active = true")
public class ActivatedRestaurant extends BaseRestaurant implements Activated {
	
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonIgnore
	private User owner;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<ReservationRequest> reservationRequests;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ActivatedRestaurant that = (ActivatedRestaurant) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
