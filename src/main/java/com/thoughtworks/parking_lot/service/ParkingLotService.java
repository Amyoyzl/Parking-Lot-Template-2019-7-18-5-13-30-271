package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository repository;

    public ParkingLot add(ParkingLot parkingLot) {
        return repository.save(parkingLot);
    }
}
