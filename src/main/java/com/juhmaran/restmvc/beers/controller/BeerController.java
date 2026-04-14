package com.juhmaran.restmvc.beers.controller;

import com.juhmaran.restmvc.beers.model.BeerDTO;
import com.juhmaran.restmvc.beers.services.BeerService;
import com.juhmaran.restmvc.exception.BreweryNotFoundException;
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

  @GetMapping
  public List<BeerDTO> listBeers() {
    return beerService.listBeers();
  }

  @GetMapping("/{beerId}")
  public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
    log.debug("Getting beer by id {} - in controller", beerId);
    return beerService.getBeerById(beerId)
      .orElseThrow(BreweryNotFoundException::new);
  }

  @PostMapping
  public ResponseEntity handlePost(@RequestBody BeerDTO beerDTO) {
    BeerDTO savedBeerDTO = beerService.saveNewBeer(beerDTO);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/v1/beer/" + savedBeerDTO.getId().toString());

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId,
                                       @RequestBody BeerDTO beerDTO) {
    beerService.updateBeerById(beerId, beerDTO);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{beerId}")
  public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId) {
    beerService.deleteBeerById(beerId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/{beerId}")
  public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId,
                                            @RequestBody BeerDTO beerDTO) {
    beerService.patchBeerById(beerId, beerDTO);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
