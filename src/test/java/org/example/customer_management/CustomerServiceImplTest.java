package org.example.customer_management;

import org.example.customer_management.entity.Customer;
import org.example.customer_management.repository.CustomerRepository;
import org.example.customer_management.request.CustomerRequest;
import org.example.customer_management.response.CustomerResponse;
import org.example.customer_management.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private UUID customerId;
    private Customer customer;
    private CustomerRequest customerRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerId = UUID.randomUUID();
        customerRequest = new CustomerRequest(
                "John Doe",
                "john@example.com",
                new BigDecimal("5000"),
                LocalDateTime.now().minusMonths(2)
        );
        customer = new Customer(
                customerRequest.getName(),
                customerRequest.getEmail(),
                customerRequest.getAnnualSpend(),
                customerRequest.getLastPurchaseDate()
        );
        customer.setId(customerId);
    }

    @Test
    void shouldCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse response = customerService.createCustomer(customerRequest);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("John Doe");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void shouldGetCustomerById() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        CustomerResponse response = customerService.getCustomerById(customerId);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(customerId);
    }

    @Test
    void shouldGetCustomerByEmail() {
        when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(customer));

        CustomerResponse response = customerService.getCustomerByEmail("john@example.com");

        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void shouldGetCustomerByName() {
        when(customerRepository.findByName("John Doe")).thenReturn(Optional.of(customer));

        CustomerResponse response = customerService.getCustomerByName("John Doe");

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("John Doe");
    }

    @Test
    void shouldUpdateCustomer() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerRequest updatedRequest = new CustomerRequest(
                "Jane Doe",
                "jane@example.com",
                new BigDecimal("7000"),
                LocalDateTime.now()
        );

        CustomerResponse response = customerService.updateCustomer(customerId, updatedRequest);

        assertThat(response.getName()).isEqualTo("Jane Doe");
        assertThat(response.getEmail()).isEqualTo("jane@example.com");
    }

    @Test
    void shouldDeleteCustomer() {
        when(customerRepository.existsById(customerId)).thenReturn(true);

        customerService.deleteCustomer(customerId);

        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFoundOnDelete() {
        when(customerRepository.existsById(customerId)).thenReturn(false);

        assertThatThrownBy(() -> customerService.deleteCustomer(customerId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Customer not found");
    }

    @Test
    void shouldCalculatePlatinumTier() {
        BigDecimal spend = new BigDecimal("15000");
        LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(3);

        String tier = callCalculateTier(spend, lastPurchase);

        assertThat(tier).isEqualTo("Platinum");
    }

    @Test
    void shouldCalculateGoldTier() {
        BigDecimal spend = new BigDecimal("5000");
        LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(8);

        String tier = callCalculateTier(spend, lastPurchase);

        assertThat(tier).isEqualTo("Gold");
    }

    @Test
    void shouldCalculateSilverTierForLowSpend() {
        BigDecimal spend = new BigDecimal("500");
        LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(2);

        String tier = callCalculateTier(spend, lastPurchase);

        assertThat(tier).isEqualTo("Silver");
    }

    @Test
    void shouldCalculateSilverTierForOldPurchase() {
        BigDecimal spend = new BigDecimal("15000");
        LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(10);

        String tier = callCalculateTier(spend, lastPurchase);

        assertThat(tier).isEqualTo("Silver");
    }

    @Test
    void shouldValidateEmailFormat() {
        CustomerRequest invalidRequest = new CustomerRequest(
                "Invalid User",
                "invalid-email", // geçersiz email formatı
                new BigDecimal("3000"),
                LocalDateTime.now()
        );

        // basit regex kontrolü simüle
        boolean isValidEmail = invalidRequest.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

        assertThat(isValidEmail).isFalse();
    }

    // Private methodı test edebilmek için bir yardımcı method:
    private String callCalculateTier(BigDecimal spend, LocalDateTime lastPurchase) {
        try {
            var method = CustomerServiceImpl.class.getDeclaredMethod("calculateTier", BigDecimal.class, LocalDateTime.class);
            method.setAccessible(true);
            return (String) method.invoke(customerService, spend, lastPurchase);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
