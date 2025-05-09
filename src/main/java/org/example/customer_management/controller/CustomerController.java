package org.example.customer_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.customer_management.request.CustomerRequest;
import org.example.customer_management.response.CustomerResponse;
import org.example.customer_management.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    // POST /customers
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET /customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable UUID id) {
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    // GET /customers?email=
    @GetMapping(params = "email")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(@RequestParam String email) {
        CustomerResponse response = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(response);
    }

    // GET /customers?name=
    @GetMapping(params = "name")
    public ResponseEntity<CustomerResponse> getCustomerByName(@RequestParam String name) {
        CustomerResponse response = customerService.getCustomerByName(name);
        return ResponseEntity.ok(response);
    }

    // PUT /customers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID id,
                                                           @Valid @RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE /customers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}