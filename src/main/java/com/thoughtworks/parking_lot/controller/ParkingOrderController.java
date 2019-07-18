package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class ParkingOrderController {

    @Autowired
    private ParkingOrderService parkingOrderService;

    @PostMapping
    public ParkingOrder add(@RequestBody ParkingOrder parkingOrder) {
        return parkingOrderService.add(parkingOrder);
    }
}
