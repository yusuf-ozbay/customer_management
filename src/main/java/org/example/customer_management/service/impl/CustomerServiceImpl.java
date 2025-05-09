package org.example.customer_management.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.customer_management.dto.CustomerDto;
import org.example.customer_management.entity.Customer;
import org.example.customer_management.enums.CustomerTier;
import org.example.customer_management.mapper.Mapper;
import org.example.customer_management.repository.CustomerRepository;
import org.example.customer_management.response.CustomerResponse;
import org.example.customer_management.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        return Mapper.toDto(customerRepository.save(customerRepository.save(Mapper.toEntity(dto))));
    }

    @Override
    public CustomerDto getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return Mapper.toDto(customer);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return Mapper.toDto(customer);
    }

    @Override
    public CustomerDto getCustomerByName(String name) {
        Customer customer = customerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return Mapper.toDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(UUID id, CustomerDto dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setAnnualSpend(dto.getAnnualSpend());
        customer.setLastPurchaseDate(dto.getLastPurchaseDate());

        Customer updated = customerRepository.save(customer);
        return Mapper.toDto(updated);
    }

    @Override
    public void deleteCustomer(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    // 🧠 Tier Hesaplama
    public static CustomerTier calculateTier(BigDecimal spend, LocalDateTime lastPurchaseDate) {
        if (spend == null) return CustomerTier.SILVER;

        if (spend.compareTo(new BigDecimal("10000")) >= 0) {
            if (lastPurchaseDate != null &&
                    ChronoUnit.MONTHS.between(lastPurchaseDate, LocalDateTime.now()) <= 6) {
                return CustomerTier.PLATINUM;
            }
        } else if (spend.compareTo(new BigDecimal("1000")) >= 0) {
            if (lastPurchaseDate != null &&
                    ChronoUnit.MONTHS.between(lastPurchaseDate, LocalDateTime.now()) <= 12) {
                return CustomerTier.GOLD;
            }
        }

        return CustomerTier.SILVER;
    }

}

