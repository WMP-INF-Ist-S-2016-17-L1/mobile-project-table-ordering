package com.table.order.restaurateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.table.order.restaurateur.model.Restaurant;

@Repository
public interface RestaurantRepository  extends JpaRepository<Restaurant, Integer> {
	
	Restaurant findByApiId(String apiId);

    boolean existsByApiId(String apiId);
}
