package com.table.order.common.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.table.order.common.security.model.User;
import com.table.order.restaurateur.model.ActivatedRestaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "active = true")
public class ReservationRequest implements Activated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id")
    private ActivatedRestaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private Set<Notification> notifications;

    private Integer numberOfPersons;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date reservationDateTime;
    
    private String clientMessage;
    private String restaurateurMessage;

    @Enumerated(EnumType.STRING)
    private ReservationRequestStatus status;
    
    @CreationTimestamp
    private Date createdDate;

    private boolean active = true;

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean value) {
        this.active = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRequest that = (ReservationRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}





