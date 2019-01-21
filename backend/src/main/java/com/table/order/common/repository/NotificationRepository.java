package com.table.order.common.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.table.order.common.model.Notification;

@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.receivedUser.username = :username "
    		+ "AND n.displayed = false ORDER BY n.dateAndTime DESC")
    Page<Notification> findAllByReceivedUserUsername(@Param("username") String username, Pageable pageable);

    @Transactional
    @Modifying
	@Query("UPDATE Notification notification SET notification.displayed = true "
			+ "WHERE notification.id = :notificationId")
    int setAsDisplayed(@Param("notificationId") Long id);
}
