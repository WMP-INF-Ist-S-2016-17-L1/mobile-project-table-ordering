package com.table.order.common.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String name;

	@Column
	private String description;

	@OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<>();

	public Role() {}
	
	public Role(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

}
