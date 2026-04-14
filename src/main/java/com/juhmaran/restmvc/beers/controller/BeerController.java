package com.juhmaran.restmvc.beers.controller;

import com.juhmaran.restmvc.beers.model.Beer;
import com.juhmaran.restmvc.beers.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

  private final BeerService beerService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Beer> listBeers() {
    return beerService.listBeers();
  }

  @RequestMapping(value = "{beerId}", method = RequestMethod.GET)
  public Beer getBeerById(@PathVariable UUID beerId) {
    log.debug("Getting beer by id {} - in controller", beerId);
    return beerService.getBeerById(beerId);
  }

  @PostMapping
  public ResponseEntity handlePost(@RequestBody Beer beer) {
    Beer savedBeer = beerService.saveNewBeer(beer);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId,
                                       @RequestBody Beer beer) {
    beerService.updateBeerById(beerId, beer);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{beerId}")
  public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId) {
    beerService.deleteBeerById(beerId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/{beerId}")
  public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId,
                                            @RequestBody Beer beer) {
    beerService.patchBeerById(beerId, beer);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
