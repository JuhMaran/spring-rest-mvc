package com.juhmaran.restmvc.beers.services;

import com.juhmaran.restmvc.beers.model.Beer;

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

  List<Beer> listBeers();

  Optional<Beer> getBeerById(UUID beerId);

  Beer saveNewBeer(Beer beer);

  void updateBeerById(UUID beerId, Beer beer);

  void deleteBeerById(UUID beerId);

  void patchBeerById(UUID beerId, Beer beer);

}
