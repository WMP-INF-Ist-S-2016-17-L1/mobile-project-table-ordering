package com.table.order.common.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.model.dto.ReservationDTO;

@RepositoryRestResource
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {

    @Query("SELECT r FROM ReservationRequest r WHERE r.client.username = ?#{ authentication?.name }")
    Page<ReservationRequest> findByCurrentClient(Pageable pageable);

    boolean existsByRestaurantOwnerUsernameAndId(String username, Long id);

    @Query("SELECT" +
            " r.id AS id," +
            " r.client.username AS clientUsername," +
            " r.numberOfPersons AS numberOfPersons," +
            " r.reservationDateTime AS reservationDateTime," +
            " r.clientMessage AS clientMessage," +
            " r.restaurateurMessage AS restaurateurMessage" +
            " FROM ReservationRequest r" +
            " WHERE r.restaurant.id = :restaurantId" +
            " AND r.status = :status" +
            " AND r.restaurant.owner.username = :ownerUsername ORDER BY r.reservationDateTime DESC")
    List<ReservationDTO> findAllByRestaurantIdAndStatus(@Param("restaurantId") Integer restaurantId,
                                                        @Param("status") ReservationRequestStatus status,
                                                        @Param("ownerUsername") String ownerUsername);
    
    @Query("SELECT r FROM ReservationRequest r WHERE r.status = :status AND r.restaurant.id = :restaurantId")
    List<ReservationRequest> findByStatusAndId(
    		@Param("status") ReservationRequestStatus status, @Param("restaurantId") int restaurantId
    );
    
    @Query("SELECT r FROM ReservationRequest r "
    		+ "WHERE r.restaurant.owner.username = ?#{ authentication?.name } "
    		+ "AND (r.reservationDateTime >= :dateFrom "
    		+ "AND r.reservationDateTime <= :dateTo "
    		+ "AND r.restaurant.id = :restaurantId)")
    List<ReservationRequest> findAllByDatesBetweenAndRestaurantId(
    		@Param("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @Param("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo,
            @Param("restaurantId") int restaurantId
    );
}
