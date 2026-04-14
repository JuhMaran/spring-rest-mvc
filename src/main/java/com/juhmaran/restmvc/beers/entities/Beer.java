package com.juhmaran.restmvc.beers.entities;

import com.juhmaran.restmvc.beers.model.BeerStyle;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Beer {

  @Id
  @UuidGenerator
  @GeneratedValue(generator = "UUID")
  @Column(length = 36, columnDefinition = "VARCHAR", updatable = false, nullable = false)
  private UUID id;

  @Version
  private Integer version;

  private String beerName;
  private BeerStyle beerStyle;
  private String upc;
  private Integer quantityOnHand;
  private BigDecimal price;

  private LocalDateTime createdDate;
  private LocalDateTime updateDate;

}
