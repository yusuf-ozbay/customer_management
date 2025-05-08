package org.example.customer_management.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.customer_management.entity.Customer;
import org.example.customer_management.repository.CustomerRepository;
import org.example.customer_management.request.CustomerRequest;
import org.example.customer_management.response.CustomerResponse;
import org.example.customer_management.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = new Customer(
                request.getName(),
                request.getEmail(),
                request.getAnnualSpend(),
                request.getLastPurchaseDate()
        );

        Customer saved = customerRepository.save(customer);
        return mapToResponse(saved);
    }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerByName(String name) {
        Customer customer = customerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAnnualSpend(request.getAnnualSpend());
        customer.setLastPurchaseDate(request.getLastPurchaseDate());

        Customer updated = customerRepository.save(customer);
        return mapToResponse(updated);
    }

    @Override
    public void deleteCustomer(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    // 🧠 Tier Hesaplama + Mapper
    private CustomerResponse mapToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setAnnualSpend(customer.getAnnualSpend());
        response.setLastPurchaseDate(customer.getLastPurchaseDate());

        response.setTier(calculateTier(customer.getAnnualSpend(), customer.getLastPurchaseDate()));
        return response;
    }

    private String calculateTier(BigDecimal spend, LocalDateTime lastPurchaseDate) {
        if (spend == null) return "Silver";

        if (spend.compareTo(new BigDecimal("10000")) >= 0) {
            if (lastPurchaseDate != null &&
                    ChronoUnit.MONTHS.between(lastPurchaseDate, LocalDateTime.now()) <= 6) {
                return "Platinum";
            }
        } else if (spend.compareTo(new BigDecimal("1000")) >= 0) {
            if (lastPurchaseDate != null &&
                    ChronoUnit.MONTHS.between(lastPurchaseDate, LocalDateTime.now()) <= 12) {
                return "Gold";
            }
        }
        return "Silver";
    }
}

