package com.juhmaran.restmvc.services;

import com.juhmaran.restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
public interface BeerService {

  List<BeerDTO> listBeers();

  Optional<BeerDTO> getBeerById(UUID beerId);

  BeerDTO saveNewBeer(BeerDTO beerDTO);

  Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO);

  Boolean deleteBeerById(UUID beerId);

  Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beerDTO);

}
