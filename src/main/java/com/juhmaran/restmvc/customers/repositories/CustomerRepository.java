package com.juhmaran.restmvc.customers.repositories;

import com.juhmaran.restmvc.customers.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
