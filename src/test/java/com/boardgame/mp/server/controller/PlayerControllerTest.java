package com.boardgame.mp.server.controller;

import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.repository.PlayerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    PlayerRepo playerRepo;

    Player player1 = new Player("Yor");

    @Test
    void getPlayerName() throws Exception {

        Mockito.when(playerRepo.getPlayerByPublicuuid(player1.getPublicuuid())).thenReturn(java.util.Optional.ofNullable(player1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/players/")
                .param("uuid", player1.getPublicuuid().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Yor")));
    }

    @Test
    void delPlayer() throws Exception {
        Mockito.when(playerRepo.getPlayerByPrivateuuid(player1.getPrivateuuid())).thenReturn(java.util.Optional.ofNullable(player1));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/players/")
                .param("uuid", player1.getPrivateuuid().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Yor")));
    }

    @Test
    void newPlayer() throws Exception {

        Mockito.when(playerRepo.save(player1)).thenReturn(player1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/players/")
                .param("name", player1.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Yor")));
    }
}