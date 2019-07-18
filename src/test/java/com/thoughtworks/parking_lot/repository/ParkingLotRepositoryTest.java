package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository repository;

    @Test
    public void should_add_parkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("parkingLot1");
        parkingLot.setCapacity(6);
        parkingLot.setLocation("zhuhai");
        long id = repository.save(parkingLot).getId();
        ParkingLot fetch = repository.findById(id).get();

        assertEquals(fetch.getName(), parkingLot.getName());
        assertEquals(fetch.getLocation(), parkingLot.getLocation());
    }
}
