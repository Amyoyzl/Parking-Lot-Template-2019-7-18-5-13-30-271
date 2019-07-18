package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ParkingOrderRepositoryTest {

    @Autowired
    private ParkingOrderRepository repository;

    private ParkingOrder parkingOrder;

    @BeforeEach
    public void init() {
        parkingOrder = new ParkingOrder();
        parkingOrder.setName("baoma");
        parkingOrder.setNumber("粤C24556");
        parkingOrder.setStartTime(new Timestamp(System.currentTimeMillis()));
        repository.save(parkingOrder);
    }

    @Test
    public void should_add_parkingOrder() {
        ParkingOrder parkingOrder4 = new ParkingOrder();
        parkingOrder4.setName("baoma");
        parkingOrder4.setNumber("粤C24556");

        ParkingOrder fetch = repository.findById(repository.save(parkingOrder4).getId()).get();

        assertEquals(fetch.getName(), parkingOrder4.getName());
        assertEquals(fetch.getNumber(), parkingOrder4.getNumber());
    }
}
