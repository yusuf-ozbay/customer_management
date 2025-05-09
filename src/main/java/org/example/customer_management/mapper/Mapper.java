package org.example.customer_management.mapper;

import jakarta.validation.Valid;
import org.example.customer_management.dto.CustomerDto;
import org.example.customer_management.entity.Customer;
import org.example.customer_management.request.CustomerRequest;
import org.example.customer_management.response.CustomerResponse;


import static org.example.customer_management.service.impl.CustomerServiceImpl.calculateTier;

public class Mapper {

    public static CustomerDto toDto(@Valid CustomerRequest request) {
        CustomerDto dto = new CustomerDto();
        dto.setName(request.getName());
        dto.setEmail(request.getEmail());
        dto.setAnnualSpend(request.getAnnualSpend());
        dto.setLastPurchaseDate(request.getLastPurchaseDate());
        return dto;
    }

    public static CustomerResponse toResponse(CustomerDto dto) {
        CustomerResponse response = new CustomerResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setEmail(dto.getEmail());
        response.setAnnualSpend(dto.getAnnualSpend());
        response.setLastPurchaseDate(dto.getLastPurchaseDate());
        response.setTier(dto.getTier());
        return response;
    }

    public static CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setAnnualSpend(customer.getAnnualSpend());
        dto.setLastPurchaseDate(customer.getLastPurchaseDate());
        dto.setTier(calculateTier(customer.getAnnualSpend(), customer.getLastPurchaseDate()));
        return dto;
    }

    public static Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setAnnualSpend(dto.getAnnualSpend());
        customer.setLastPurchaseDate(dto.getLastPurchaseDate());
        return customer;
    }
}
