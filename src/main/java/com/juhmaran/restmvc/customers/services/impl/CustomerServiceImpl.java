package com.juhmaran.restmvc.customers.services.impl;

import com.juhmaran.restmvc.customers.model.Customer;
import com.juhmaran.restmvc.customers.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class CustomerServiceImpl implements CustomerService {

  private Map<UUID, Customer> customerMap;

  public CustomerServiceImpl() {
    Customer customer1 = Customer.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name("Customer 1")
      .email("customer1@example.com")
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    Customer customer2 = Customer.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name("Customer 2")
      .email("customer2@example.com")
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    Customer customer3 = Customer.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name("Customer 3")
      .email("customer3@example.com")
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    customerMap = new HashMap<>();
    customerMap.put(customer1.getId(), customer1);
    customerMap.put(customer2.getId(), customer2);
    customerMap.put(customer3.getId(), customer3);
  }

  @Override
  public List<Customer> listCustomers() {
    return new ArrayList<>(customerMap.values());
  }

  @Override
  public Customer getCustomerById(UUID customerId) {
    return customerMap.get(customerId);
  }

  @Override
  public Customer saveNewCustomer(Customer customer) {
    Customer savedCustomer = Customer.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name(customer.getName())
      .email(customer.getEmail())
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();
    customerMap.put(savedCustomer.getId(), savedCustomer);
    return savedCustomer;
  }

  @Override
  public void updateCustomerById(UUID customerId, Customer customer) {
    Customer existingCustomer = customerMap.get(customerId);
    existingCustomer.setName(customer.getName());
    existingCustomer.setEmail(customer.getEmail());
  }

  @Override
  public void deleteCustomerById(UUID customerId) {
    customerMap.remove(customerId);
  }

}
