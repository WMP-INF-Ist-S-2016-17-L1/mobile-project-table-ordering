package com.table.order.client.service;

import com.table.order.common.model.ReservationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientServiceTest {

    private ClientService clientService;

    @BeforeEach
    void setUp(){
        clientService = mock(ClientService.class);
    }

//    @Test
//    public void deleteReservationTest(){
//        ReservationRequest reservationRequest = new ReservationRequest();
//        reservationRequest.setActive(true);
//        expected = clientService.deleteReservation(reservationRequest);
//        assertTrue(expected.isActive());
//    }

}