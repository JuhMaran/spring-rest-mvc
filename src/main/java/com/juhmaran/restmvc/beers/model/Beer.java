package com.juhmaran.restmvc.beers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Data
@Builder
@AllArgsConstructor
public class Beer {

  private UUID id;
  private Integer version;

  private String beerName;
  private BeerStyle beerStyle;
  private String upc;
  private Integer quantityOnHand;
  private BigDecimal price;

  private LocalDateTime createdDate;
  private LocalDateTime updateDate;

}
