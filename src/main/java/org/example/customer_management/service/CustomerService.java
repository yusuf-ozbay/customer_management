package org.example.customer_management.service;

import org.example.customer_management.dto.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto dto);

    CustomerDto getCustomerById(UUID id);

    CustomerDto getCustomerByEmail(String email);

    CustomerDto getCustomerByName(String name);

    CustomerDto updateCustomer(UUID id, CustomerDto dto);

    void deleteCustomer(UUID id);
}

