package org.example.customer_management;

import org.example.customer_management.dto.CustomerDto;
import org.example.customer_management.entity.Customer;
import org.example.customer_management.enums.CustomerTier;
import org.example.customer_management.mapper.Mapper;
import org.example.customer_management.repository.CustomerRepository;
import org.example.customer_management.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    private CustomerRepository customerRepository;
    private CustomerServiceImpl customerService;

    private CustomerDto sampleDto;
    private Customer sampleEntity;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);

        // Ortak test verileri
        sampleDto = new CustomerDto();
        sampleDto.setId(UUID.randomUUID());
        sampleDto.setName("John Doe");
        sampleDto.setEmail("john@example.com");
        sampleDto.setAnnualSpend(new BigDecimal("2000"));
        sampleDto.setLastPurchaseDate(LocalDateTime.now().minusMonths(5));

        sampleEntity = new Customer();
        sampleEntity.setId(sampleDto.getId());
        sampleEntity.setName(sampleDto.getName());
        sampleEntity.setEmail(sampleDto.getEmail());
        sampleEntity.setAnnualSpend(sampleDto.getAnnualSpend());
        sampleEntity.setLastPurchaseDate(sampleDto.getLastPurchaseDate());
    }

    @Test
    void testCreateCustomer() {
        try (MockedStatic<Mapper> mocked = Mockito.mockStatic(Mapper.class)) {
            mocked.when(() -> Mapper.toEntity(sampleDto)).thenReturn(sampleEntity);
            mocked.when(() -> Mapper.toDto(sampleEntity)).thenReturn(sampleDto);

            when(customerRepository.save(sampleEntity)).thenReturn(sampleEntity);

            CustomerDto result = customerService.createCustomer(sampleDto);

            assertNotNull(result);
            assertEquals("John Doe", result.getName());
        }
    }

    @Test
    void testGetCustomerById_Success() {
        try (MockedStatic<Mapper> mocked = Mockito.mockStatic(Mapper.class)) {
            when(customerRepository.findById(sampleDto.getId())).thenReturn(Optional.of(sampleEntity));
            mocked.when(() -> Mapper.toDto(sampleEntity)).thenReturn(sampleDto);

            CustomerDto result = customerService.getCustomerById(sampleDto.getId());

            assertNotNull(result);
            assertEquals("john@example.com", result.getEmail());
        }
    }

    @Test
    void testGetCustomerById_NotFound() {
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> customerService.getCustomerById(id));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void testGetCustomerByEmail_Success() {
        try (MockedStatic<Mapper> mocked = Mockito.mockStatic(Mapper.class)) {
            when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(sampleEntity));
            mocked.when(() -> Mapper.toDto(sampleEntity)).thenReturn(sampleDto);

            CustomerDto result = customerService.getCustomerByEmail("john@example.com");

            assertNotNull(result);
            assertEquals("John Doe", result.getName());
        }
    }

    @Test
    void testGetCustomerByName_Success() {
        try (MockedStatic<Mapper> mocked = Mockito.mockStatic(Mapper.class)) {
            when(customerRepository.findByName("John Doe")).thenReturn(Optional.of(sampleEntity));
            mocked.when(() -> Mapper.toDto(sampleEntity)).thenReturn(sampleDto);

            CustomerDto result = customerService.getCustomerByName("John Doe");

            assertNotNull(result);
            assertEquals("john@example.com", result.getEmail());
        }
    }

    @Test
    void testUpdateCustomer_Success() {
        try (MockedStatic<Mapper> mocked = Mockito.mockStatic(Mapper.class)) {
            when(customerRepository.findById(sampleDto.getId())).thenReturn(Optional.of(sampleEntity));
            when(customerRepository.save(any(Customer.class))).thenReturn(sampleEntity);
            mocked.when(() -> Mapper.toDto(any(Customer.class))).thenReturn(sampleDto);

            CustomerDto result = customerService.updateCustomer(sampleDto.getId(), sampleDto);

            assertNotNull(result);
            assertEquals(sampleDto.getEmail(), result.getEmail());
        }
    }

    @Test
    void testUpdateCustomer_NotFound() {
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> customerService.updateCustomer(id, sampleDto));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void testDeleteCustomer_Success() {
        UUID id = UUID.randomUUID();
        when(customerRepository.existsById(id)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(id);

        assertDoesNotThrow(() -> customerService.deleteCustomer(id));
    }

    @Test
    void testDeleteCustomer_NotFound() {
        UUID id = UUID.randomUUID();
        when(customerRepository.existsById(id)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> customerService.deleteCustomer(id));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void testCalculateTier_Platinum() {
        BigDecimal spend = new BigDecimal("15000");
        LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(3);

        CustomerTier tier = CustomerServiceImpl.calculateTier(spend, lastPurchase);
        assertEquals(CustomerTier.PLATINUM, tier);
    }

    @Test
    void testCalculateTier_Gold() {
        BigDecimal spend = new BigDecimal("3000");
        LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(10);

        CustomerTier tier = CustomerServiceImpl.calculateTier(spend, lastPurchase);
        assertEquals(CustomerTier.GOLD, tier);
    }

    @Test
    void testCalculateTier_Silver() {
        BigDecimal spend = new BigDecimal("500");
        LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(8);

        CustomerTier tier = CustomerServiceImpl.calculateTier(spend, lastPurchase);
        assertEquals(CustomerTier.SILVER, tier);
    }

    @Test
    void testCalculateTier_NullSpend() {
        CustomerTier tier = CustomerServiceImpl.calculateTier(null, LocalDateTime.now());
        assertEquals(CustomerTier.SILVER, tier);
    }
}
