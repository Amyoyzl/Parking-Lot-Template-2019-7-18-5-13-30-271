package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository repository;

    public ParkingOrder add(ParkingOrder parkingOrder) {
        return repository.save(parkingOrder);
    }

    public ParkingOrder update(long id) {
        Optional<ParkingOrder> optional = repository.findById(id);
        if(optional.isPresent()) {
            ParkingOrder parkingOrder = optional.get();
            parkingOrder.setState(false);
            parkingOrder.setEndTime(new Timestamp(System.currentTimeMillis()));
            return repository.save(parkingOrder);
        }
        return null;
    }
}
