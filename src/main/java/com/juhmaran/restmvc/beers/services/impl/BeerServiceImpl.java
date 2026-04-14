package com.juhmaran.restmvc.beers.services.impl;

import com.juhmaran.restmvc.beers.model.BeerDTO;
import com.juhmaran.restmvc.beers.model.enums.BeerStyle;
import com.juhmaran.restmvc.beers.services.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

  private Map<UUID, BeerDTO> beerMap;

  public BeerServiceImpl() {
    this.beerMap = new HashMap<>();

    BeerDTO beerDTO1 = BeerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .beerName("Galaxy Cat")
      .beerStyle(BeerStyle.PALE_ALE)
      .upc("12356")
      .price(new BigDecimal("12.99"))
      .quantityOnHand(122)
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    BeerDTO beerDTO2 = BeerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .beerName("Crank")
      .beerStyle(BeerStyle.PALE_ALE)
      .upc("12356222")
      .price(new BigDecimal("11.99"))
      .quantityOnHand(392)
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    BeerDTO beerDTO3 = BeerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .beerName("Sunshine City")
      .beerStyle(BeerStyle.IPA)
      .upc("12356")
      .price(new BigDecimal("13.99"))
      .quantityOnHand(144)
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    beerMap.put(beerDTO1.getId(), beerDTO1);
    beerMap.put(beerDTO2.getId(), beerDTO2);
    beerMap.put(beerDTO3.getId(), beerDTO3);

  }

  @Override
  public List<BeerDTO> listBeers() {
    return new ArrayList<>(beerMap.values());
  }

  @Override
  public Optional<BeerDTO> getBeerById(UUID beerId) {
    log.debug("Get Beer by ID: {} - in service", beerId);
    return Optional.ofNullable(beerMap.get(beerId));
  }

  @Override
  public BeerDTO saveNewBeer(BeerDTO beerDTO) {
    BeerDTO savedBeerDTO = BeerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .beerName(beerDTO.getBeerName())
      .beerStyle(beerDTO.getBeerStyle())
      .upc(beerDTO.getUpc())
      .quantityOnHand(beerDTO.getQuantityOnHand())
      .price(beerDTO.getPrice())
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();
    beerMap.put(savedBeerDTO.getId(), savedBeerDTO);
    return savedBeerDTO;
  }

  @Override
  public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
    BeerDTO existing = beerMap.get(beerId);
    existing.setBeerName(beerDTO.getBeerName());
    existing.setBeerStyle(beerDTO.getBeerStyle());
    existing.setUpc(beerDTO.getUpc());
    existing.setQuantityOnHand(beerDTO.getQuantityOnHand());
    existing.setPrice(beerDTO.getPrice());
  }

  @Override
  public void deleteBeerById(UUID beerId) {
    beerMap.remove(beerId);
  }

  @Override
  public void patchBeerById(UUID beerId, BeerDTO beerDTO) {
    BeerDTO existing = beerMap.get(beerId);

    if (StringUtils.hasText(beerDTO.getBeerName())) {
      existing.setBeerName(beerDTO.getBeerName());
    }

    if (beerDTO.getBeerStyle() != null) {
      existing.setBeerStyle(beerDTO.getBeerStyle());
    }

    if (StringUtils.hasText(beerDTO.getUpc())) {
      existing.setUpc(beerDTO.getUpc());
    }

    if (beerDTO.getQuantityOnHand() != null) {
      existing.setQuantityOnHand(beerDTO.getQuantityOnHand());
    }

    if (beerDTO.getPrice() != null) {
      existing.setPrice(beerDTO.getPrice());
    }

  }

}
