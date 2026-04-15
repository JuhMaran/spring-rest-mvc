package com.juhmaran.restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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
public class Customer {

  @Id
  @UuidGenerator
  @GeneratedValue(generator = "UUID")
  @Column(length = 36, columnDefinition = "VARCHAR", updatable = false, nullable = false)
  private UUID id;

  @Version
  private Integer version;

  private String name;
  private String email;

  private LocalDateTime createdDate;
  private LocalDateTime updateDate;

}
