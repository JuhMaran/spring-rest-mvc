package com.juhmaran.restmvc.customers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
public class Customer {

  private UUID id;
  private Integer version;

  private String name;
  private String email;

  private LocalDateTime createdDate;
  private LocalDateTime updateDate;

}
