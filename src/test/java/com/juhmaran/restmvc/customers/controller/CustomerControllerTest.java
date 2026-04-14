package com.juhmaran.restmvc.customers.controller;

import com.juhmaran.restmvc.customers.model.CustomerDTO;
import com.juhmaran.restmvc.customers.services.CustomerService;
import com.juhmaran.restmvc.customers.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes unitários da camada de Controller para {@link CustomerController}
 */
@WebMvcTest(CustomerController.class)
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

  public static final String CUSTOMER_PATH = "/api/v1/customer";
  public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockitoBean
  CustomerService customerService;

  CustomerServiceImpl customerServiceImpl;

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @BeforeEach
  void setUp() {
    customerServiceImpl = new CustomerServiceImpl();
  }

  @Test
  @DisplayName("Should return list of customers")
  void shouldReturnCustomerList() throws Exception {

    given(customerService.listCustomers())
      .willReturn(customerServiceImpl.listCustomers());

    mockMvc.perform(get(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.length()", is(3)));
  }

  @Test
  @DisplayName("Should return customer by ID")
  void shouldReturnCustomerById() throws Exception {
    CustomerDTO customerDTO = customerServiceImpl.listCustomers().getFirst();

    given(customerService.getCustomerById(customerDTO.getId()))
      .willReturn(customerDTO);

    mockMvc.perform(get(CUSTOMER_PATH_ID, customerDTO.getId())
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id", is(customerDTO.getId().toString())))
      .andExpect(jsonPath("$.name", is(customerDTO.getName())));
  }

  @Test
  @DisplayName("Should create a new customer and return 201 with Location  header")
  void shouldCreateNewCustomer() throws Exception {
    CustomerDTO customerDTO = customerServiceImpl.listCustomers().getFirst();
    customerDTO.setId(null);
    customerDTO.setVersion(null);

    given(customerService.saveNewCustomer(any(CustomerDTO.class)))
      .willReturn(customerServiceImpl.listCustomers().get(1));

    mockMvc.perform(post(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(customerDTO)))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"));
  }

  @Test
  @DisplayName("Should update customer completely and return 204")
  void shouldUpdateCustomer() throws Exception {
    CustomerDTO customerDTO = customerServiceImpl.listCustomers().getFirst();

    mockMvc.perform(put(CUSTOMER_PATH_ID, customerDTO.getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(customerDTO)))
      .andExpect(status().isNoContent());

    verify(customerService).updateCustomerById(any(UUID.class), any(CustomerDTO.class));
  }

  @Test
  @DisplayName("Should delete customer by id and return 204")
  void shouldDeleteCustomer() throws Exception {
    CustomerDTO customerDTO = customerServiceImpl.listCustomers().getFirst();

    mockMvc.perform(delete(CUSTOMER_PATH_ID, customerDTO.getId())
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    verify(customerService).deleteCustomerById(uuidArgumentCaptor.capture());
    assertThat(customerDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
  }

}