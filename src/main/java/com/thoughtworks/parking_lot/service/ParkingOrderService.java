package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.exception.NoPositionException;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository repository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingOrder add(ParkingOrder parkingOrder) throws NoPositionException {
        if(validPositions(parkingOrder) > 0) {
            return repository.save(parkingOrder);
        }
        throw new NoPositionException();
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

    public int validPositions(ParkingOrder parkingOrder) {
        String name = parkingOrder.getParkingLot().getName();
        int carSize = (int)repository.findAll().stream().filter(order -> order.getState() && order.getParkingLot().getName().equals(name)).count();
        return parkingOrder.getParkingLot().getCapacity() - carSize;
    }
}
