package com.juhmaran.restmvc.services;

import com.juhmaran.restmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
public interface CustomerService {

  List<CustomerDTO> listCustomers();

  CustomerDTO getCustomerById(UUID customerId);

  CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

  void updateCustomerById(UUID customerId, CustomerDTO customerDTO);

  void deleteCustomerById(UUID customerId);

}
