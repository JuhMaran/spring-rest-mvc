package com.juhmaran.restmvc.beers.controller;

import com.juhmaran.restmvc.beers.model.Beer;
import com.juhmaran.restmvc.beers.services.BeerService;
import com.juhmaran.restmvc.beers.services.impl.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes unitários da camada de Controller para {@link BeerController}
 */
@WebMvcTest(BeerController.class)
@ExtendWith(MockitoExtension.class)
class BeerControllerTest {

  public static final String BEER_PATH = "/api/v1/beer";
  public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockitoBean
  BeerService beerService;

  BeerServiceImpl beerServiceImpl;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @Captor
  ArgumentCaptor<Beer> beerArgumentCaptor;

  @BeforeEach
  void setUp() {
    beerServiceImpl = new BeerServiceImpl();
  }

  @Test
  @DisplayName("Should return list of beers")
  void shouldReturnBeerList() throws Exception {

    // given
    given(beerService.listBeers())
      .willReturn(beerServiceImpl.listBeers());

    // when / then
    mockMvc.perform(get(BEER_PATH)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.length()", is(3)));
  }

  @Test
  @DisplayName("Should return beer by id")
  void shouldReturnBeerById() throws Exception {
    Beer testBeer = beerServiceImpl.listBeers().getFirst();

    given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));

    mockMvc.perform(get(BEER_PATH_ID, testBeer.getId())
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
      .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));

  }

  @Test
  @DisplayName("Should create a new beer and return 201 with Location header")
  void shouldCreateNewBeer() throws Exception {
    Beer beer = beerServiceImpl.listBeers().getFirst();
    beer.setId(null);
    beer.setVersion(null);

    given(beerService.saveNewBeer(any(Beer.class)))
      .willReturn(beerServiceImpl.listBeers().get(1));

    mockMvc.perform(post(BEER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(beer)))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"));
  }

  @Test
  @DisplayName("Should update beer completely and return 204")
  void shouldUpdateBeer() throws Exception {
    Beer beer = beerServiceImpl.listBeers().getFirst();

    mockMvc.perform(put(BEER_PATH_ID, beer.getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(beer)))
      .andExpect(status().isNoContent());

    verify(beerService).updateBeerById(any(UUID.class), any(Beer.class));
  }

  @Test
  @DisplayName("Should delete beer by id and return 204")
  void shouldDeleteBeer() throws Exception {
    Beer beer = beerServiceImpl.listBeers().getFirst();

    mockMvc.perform(delete(BEER_PATH_ID, beer.getId())
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    verify(beerService).deleteBeerById(uuidArgumentCaptor.capture());
    assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
  }

  @Test
  @DisplayName("Should partially update beer name")
  void shouldPatchBeerName() throws Exception {
    Beer beer = beerServiceImpl.listBeers().getFirst();

    Map<String, Object> beerMap = new HashMap<>();
    beerMap.put("beerName", "New Name");

    mockMvc.perform(patch(BEER_PATH_ID, beer.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(beerMap)))
      .andExpect(status().isNoContent());

    verify(beerService).patchBeerById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());

    assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
  }

}