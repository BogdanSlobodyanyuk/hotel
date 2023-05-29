package com.robot.hotel.service;

import com.robot.hotel.domain.Guest;
import com.robot.hotel.dto.GuestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = GuestService.class)
class GuestServiceTest {


    @MockBean
    private GuestService guestService;
    @Test
    void findAllGuests() {
        when(guestService.findAllGuests()).thenReturn(List.of());
    }

    @Test
    void findGuestForId() {
    }

    @Test
    void findGuestForName() {
    }

    @Test
    void findGuestForNameAndPassport() {
    }

    @Test
    void addRoom() {
    }

    @Test
    void saveGuest() {
    }
}