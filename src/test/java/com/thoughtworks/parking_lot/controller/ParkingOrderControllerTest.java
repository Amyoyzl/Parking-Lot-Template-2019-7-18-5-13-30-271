package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void should_add_parkingOrder() throws Exception {
        when(parkingOrderService.add(any())).thenReturn(parkingOrder);
        ResultActions resultActions = mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(parkingOrder)));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(parkingOrder.getName())));
    }
}
