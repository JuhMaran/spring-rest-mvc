package com.juhmaran.restmvc.controller;

import com.juhmaran.restmvc.model.CustomerDTO;
import com.juhmaran.restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping
  public List<CustomerDTO> getAllCustomers() {
    return customerService.listCustomers();
  }

  @GetMapping("/{customerId}")
  public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId) {
    log.debug("Getting customer by id {} - in controller", customerId);
    return customerService.getCustomerById(customerId);
  }

  @PostMapping
  public ResponseEntity handlePost(@RequestBody CustomerDTO customerDTO) {
    CustomerDTO savedCustomerDTO = customerService.saveNewCustomer(customerDTO);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/v1/customer/" + savedCustomerDTO.getId().toString());

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @PutMapping("/{customerId}")
  public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId,
                                           @RequestBody CustomerDTO customerDTO) {
    customerService.updateCustomerById(customerId, customerDTO);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID customerId) {
    customerService.deleteCustomerById(customerId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}
