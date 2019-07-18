package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ParkingLotService parkingLotService;

    @Test
    public void should_add_parkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("parkingLot");
        parkingLot.setCapacity(3);
        parkingLot.setLocation("zhuhai");

        when(parkingLotService.add(any())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(post("/parkingLots")
                .content(new ObjectMapper().writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk()).andExpect(jsonPath("$.name", is(parkingLot.getName())));

    }
}
