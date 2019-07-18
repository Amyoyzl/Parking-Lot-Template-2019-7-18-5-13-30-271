package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.exception.NoPositionException;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Timestamp;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingOrderController.class)
public class ParkingOrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingOrderService parkingOrderService;

    private ParkingOrder parkingOrder;

    @BeforeEach
    public void init() {
        parkingOrder = new ParkingOrder();
        parkingOrder.setName("baoma");
        parkingOrder.setNumber("粤C24556");
        parkingOrder.setStartTime(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void should_add_parkingOrder() {
        try {
            when(parkingOrderService.add(any())).thenReturn(parkingOrder);
            ResultActions resultActions = mvc.perform(post("/orders")
                    .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(parkingOrder)));
            resultActions.andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(parkingOrder.getName())));
            verify(parkingOrderService).add(any());
        } catch (Exception | NoPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_add_parkingOrder_when_no_position() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("parkingLot1");
        parkingLot.setCapacity(1);
        parkingLot.setLocation("beijing");
        parkingOrder.setParkingLot(parkingLot);
        ParkingOrder parkingOrder1 = new ParkingOrder();
        parkingOrder1.setName("baoma");
        parkingOrder1.setNumber("粤C24555");
        parkingOrder1.setStartTime(new Timestamp(System.currentTimeMillis()));
        parkingOrder1.setParkingLot(parkingLot);
        try {
            parkingOrderService.add(parkingOrder1);
            when(parkingOrderService.add(any())).thenThrow(new NoPositionException());
            ResultActions resultActions = mvc.perform(post("/orders")
                    .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(parkingOrder)));
            resultActions.andExpect(status().isOk());
            verify(parkingOrderService).add(any());
        } catch (NoPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_update_parkingOrder() throws Exception {
        try {
            parkingOrderService.add(parkingOrder);
            parkingOrder.setEndTime(new Timestamp(System.currentTimeMillis()));
            parkingOrder.setState(false);
            when(parkingOrderService.update(anyInt())).thenReturn(parkingOrder);
            ResultActions resultActions = mvc.perform(put("/orders/1"));
            resultActions.andExpect(status().isOk());
        } catch (NoPositionException e) {
            e.printStackTrace();
        }
    }
}
