package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository repository;
    private ParkingLot parkingLot;

    @BeforeEach
    public void init() {
        parkingLot = new ParkingLot();
        parkingLot.setName("parkingLot");
        parkingLot.setCapacity(6);
        parkingLot.setLocation("zhuhai");
        repository.save(parkingLot);
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setName("parkingLot1");
        parkingLot1.setCapacity(6);
        parkingLot1.setLocation("zhuhai");
        repository.save(parkingLot1);
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setName("parkingLot2");
        parkingLot2.setCapacity(6);
        parkingLot2.setLocation("zhuhai");
        repository.save(parkingLot2);
    }

    @Test
    public void should_add_parkingLot() {
        ParkingLot parkingLot3 = new ParkingLot();
        parkingLot3.setName("parkingLot3");
        parkingLot3.setCapacity(6);
        parkingLot3.setLocation("zhuhai");

        repository.save(parkingLot3);
        ParkingLot fetch = repository.findById(parkingLot3.getName()).get();

        assertEquals(fetch.getCapacity(), parkingLot3.getCapacity());
        assertEquals(fetch.getLocation(), parkingLot3.getLocation());
    }

    @Test
    public void should_delete_parkingLot() {
        repository.deleteById(parkingLot.getName());
        boolean exist = repository.findById(parkingLot.getName()).isPresent();

        assertFalse(exist);
    }

    @Test
    public void should_return_parkingLots_by_page() {
        List<ParkingLot> parkingLots = repository.findAll();
    }
}
