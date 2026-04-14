package com.juhmaran.restmvc.customers.mappers;

import com.juhmaran.restmvc.customers.entities.Customer;
import com.juhmaran.restmvc.customers.model.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Mapper
public interface CustomerMapper {

  Customer customerDtoToCustomer(CustomerDTO customerDTO);

  CustomerDTO customerToCustomerDto(Customer customer);

}
