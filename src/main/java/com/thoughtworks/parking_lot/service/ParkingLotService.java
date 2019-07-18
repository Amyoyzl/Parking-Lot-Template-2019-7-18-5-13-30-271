package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository repository;

    public ParkingLot add(ParkingLot parkingLot) {
        return repository.save(parkingLot);
    }

    public void delete(String name) {
        repository.deleteById(name);
    }

    public List<ParkingLot> getAllByPage(int page) {
        return repository.findAll().stream().skip((page-1)*15).limit(15).collect(Collectors.toList());
    }

    public ParkingLot getById(String name) {
        Optional<ParkingLot> optional = repository.findById(name);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public ParkingLot updateCapacity(int capacity, String name) {
        ParkingLot parkingLot = getById(name);
        parkingLot.setCapacity(capacity);
        return repository.save(parkingLot);
    }
}
