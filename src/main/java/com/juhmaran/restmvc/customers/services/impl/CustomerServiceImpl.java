package com.juhmaran.restmvc.customers.services.impl;

import com.juhmaran.restmvc.customers.model.CustomerDTO;
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

  private Map<UUID, CustomerDTO> customerMap;

  public CustomerServiceImpl() {
    CustomerDTO customerDTO1 = CustomerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name("Customer 1")
      .email("customer1@example.com")
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    CustomerDTO customerDTO2 = CustomerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name("Customer 2")
      .email("customer2@example.com")
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    CustomerDTO customerDTO3 = CustomerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name("Customer 3")
      .email("customer3@example.com")
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();

    customerMap = new HashMap<>();
    customerMap.put(customerDTO1.getId(), customerDTO1);
    customerMap.put(customerDTO2.getId(), customerDTO2);
    customerMap.put(customerDTO3.getId(), customerDTO3);
  }

  @Override
  public List<CustomerDTO> listCustomers() {
    return new ArrayList<>(customerMap.values());
  }

  @Override
  public CustomerDTO getCustomerById(UUID customerId) {
    return customerMap.get(customerId);
  }

  @Override
  public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
    CustomerDTO savedCustomerDTO = CustomerDTO.builder()
      .id(UUID.randomUUID())
      .version(1)
      .name(customerDTO.getName())
      .email(customerDTO.getEmail())
      .createdDate(LocalDateTime.now())
      .updateDate(LocalDateTime.now())
      .build();
    customerMap.put(savedCustomerDTO.getId(), savedCustomerDTO);
    return savedCustomerDTO;
  }

  @Override
  public void updateCustomerById(UUID customerId, CustomerDTO customerDTO) {
    CustomerDTO existingCustomerDTO = customerMap.get(customerId);
    existingCustomerDTO.setName(customerDTO.getName());
    existingCustomerDTO.setEmail(customerDTO.getEmail());
  }

  @Override
  public void deleteCustomerById(UUID customerId) {
    customerMap.remove(customerId);
  }

}
