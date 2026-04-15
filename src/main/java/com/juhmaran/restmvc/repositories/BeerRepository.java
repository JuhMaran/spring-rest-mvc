package com.juhmaran.restmvc.repositories;

import com.juhmaran.restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
