package com.juhmaran.restmvc.customers.services;

import com.juhmaran.restmvc.customers.model.Customer;

import java.util.List;
import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
public interface CustomerService {

  List<Customer> listCustomers();

  Customer getCustomerById(UUID customerId);

  Customer saveNewCustomer(Customer customer);

  void updateCustomerById(UUID customerId, Customer customer);

  void deleteCustomerById(UUID customerId);

}
