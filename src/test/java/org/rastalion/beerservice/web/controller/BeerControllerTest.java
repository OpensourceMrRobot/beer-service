package org.rastalion.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rastalion.beerservice.bootstrap.BeerLoader;
import org.rastalion.beerservice.services.BeerService;
import org.rastalion.beerservice.web.model.BeerDto;
import org.rastalion.beerservice.web.model.BeerStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Because we are using the WebMvcTest we are not passing through the service layer.

That's the reason we made a MockBean of our beerService and use the static import of given method from mockito
 */

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    String url;
    BeerDto validBeerDto;

    @BeforeEach
    void setUp() {
        url = "/api/v1/beer/";
        validBeerDto = BeerDto.builder()
                .beerName("Duvel")
                .beerStyle(BeerStyle.ALE)
                .ean(BeerLoader.BEER_1_EAN)
                .price(new BigDecimal("2.95"))
                .build();
    }

    @Test
    void getBeerById() throws Exception {

        given(beerService.getById(any())).willReturn(validBeerDto);

        mockMvc.perform(get(url + UUID.randomUUID())
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        String beerDtoJson = objectMapper.writeValueAsString(validBeerDto);

        given(beerService.saveNewBeer(any())).willReturn(validBeerDto);

        mockMvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        given(beerService.updateBeer(any(), any())).willReturn(validBeerDto);

        String beerDtoJson = objectMapper.writeValueAsString(validBeerDto);


        mockMvc.perform(put(url + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @AfterEach
    void tearDown() {
        url = null;
        validBeerDto = null;
    }
}