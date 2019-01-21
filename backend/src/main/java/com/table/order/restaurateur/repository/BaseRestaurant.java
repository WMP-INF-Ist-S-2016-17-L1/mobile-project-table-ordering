package com.table.order.restaurateur.repository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseRestaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(unique = true, name = "api_id")
	protected String apiId;

	protected String name;

	protected String city;

	protected String street;

	protected boolean active;
}
