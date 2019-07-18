package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping
    public ParkingLot addParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.add(parkingLot);
    }

    @DeleteMapping("/{name}")
    public void deleteParkingLot(@PathVariable String name) {
        parkingLotService.delete(name);
    }

    @GetMapping(params = "page")
    public List<ParkingLot> getParkingLotByPage(@RequestParam int page) {
        return parkingLotService.getAllByPage(page);
    }

    @GetMapping("/{name}")
    public ParkingLot getById(@PathVariable String name) {
        return parkingLotService.getById(name);
    }

}
