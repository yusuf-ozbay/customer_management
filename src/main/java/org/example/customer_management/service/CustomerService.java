package org.example.customer_management.service;

import org.example.customer_management.request.CustomerRequest;
import org.example.customer_management.response.CustomerResponse;

import java.util.UUID;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerById(UUID id);

    CustomerResponse getCustomerByEmail(String email);

    CustomerResponse getCustomerByName(String name);

    CustomerResponse updateCustomer(UUID id, CustomerRequest request);

    void deleteCustomer(UUID id);
}

