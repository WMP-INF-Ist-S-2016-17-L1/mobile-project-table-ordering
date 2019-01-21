package com.table.order.restaurateur.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.table.order.restaurateur.model.ActivatedRestaurant;

@RepositoryRestResource
public interface ActivatedRestaurantRepository extends PagingAndSortingRepository<ActivatedRestaurant, Integer> {

	ActivatedRestaurant findByApiId(@Param("apiId") String apiId);

	boolean existsByApiId(String apiId);

	@Query("SELECT r FROM ActivatedRestaurant r WHERE r.owner.username = ?#{ authentication?.name }")
	Page<ActivatedRestaurant> findAllByCurrentUser(Pageable pageable);
}
