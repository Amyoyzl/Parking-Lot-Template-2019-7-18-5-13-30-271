package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository repository;

    public ParkingOrder add(ParkingOrder parkingOrder) {
        return repository.save(parkingOrder);
    }
}
