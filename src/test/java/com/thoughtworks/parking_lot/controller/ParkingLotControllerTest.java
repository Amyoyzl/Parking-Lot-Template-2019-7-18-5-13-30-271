package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingLotService parkingLotService;

    ParkingLot parkingLot;
    List<ParkingLot> parkingLots = new ArrayList<>();

    @BeforeEach
    public void init() {
        parkingLot = new ParkingLot();
        parkingLot.setName("parkingLot");
        parkingLot.setCapacity(3);
        parkingLot.setLocation("zhuhai");
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setName("parkingLot1");
        parkingLot1.setCapacity(3);
        parkingLot1.setLocation("zhuhai");
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setName("parkingLot2");
        parkingLot2.setCapacity(3);
        parkingLot2.setLocation("zhuhai");
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
    }

    @Test
    public void should_add_parkingLot() throws Exception {
        when(parkingLotService.add(any())).thenReturn(parkingLot);
        ResultActions result = mvc.perform(post("/parkingLots")
                .content(new ObjectMapper().writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk()).andExpect(jsonPath("$.name", is(parkingLot.getName())));
        verify(parkingLotService).add(any());
    }

    @Test
    public void should_delete_parkingLot() throws Exception {
        ResultActions result = mvc.perform(delete("/parkingLots/name"));
        result.andExpect(status().isOk());
        verify(parkingLotService).delete(anyString());
    }

    @Test
    public void should_get_parkingLot_by_page() throws Exception {
        when(parkingLotService.getAllByPage(anyInt())).thenReturn(parkingLots);
        ResultActions resultActions = mvc.perform(get("/parkingLots").param("page", "1"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(parkingLots.get(0).getName())))
                .andExpect(jsonPath("$[1].capacity", is(parkingLots.get(1).getCapacity())));
        verify(parkingLotService).getAllByPage(anyInt());
    }

    @Test
    public void should_get_parkingLot_by_id() throws Exception {
        when(parkingLotService.getById(anyString())).thenReturn(parkingLot);
        ResultActions resultActions = mvc.perform(get("/parkingLots/{id}", anyInt()));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(parkingLot.getName())))
                .andExpect(jsonPath("$.capacity", is(parkingLot.getCapacity())));
        verify(parkingLotService).getById(anyString());
    }

    @Test
    public void should_update_parkingLot_capacity() throws Exception {
        when(parkingLotService.updateCapacity(anyInt(), anyString())).thenReturn(parkingLot);
        ResultActions resultActions = mvc.perform(put("/parkingLots")
                .param("capacity", "3").param("name","parkingLot"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("parkingLot")))
                .andExpect(jsonPath("$.capacity", is(3)));
        verify(parkingLotService).updateCapacity(anyInt(), anyString());
    }

}
