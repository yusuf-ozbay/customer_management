package org.example.customer_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.customer_management.mapper.Mapper;
import org.example.customer_management.request.CustomerRequest;
import org.example.customer_management.response.CustomerResponse;
import org.example.customer_management.rest.BaseController;
import org.example.customer_management.rest.MetaResponse;
import org.example.customer_management.rest.Response;
import org.example.customer_management.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // POST /customers
    @PostMapping
    public Response<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        return respond(Mapper.toResponse(customerService.createCustomer(Mapper.toDto(request))));
    }

    // GET /customers/{id}
    @GetMapping("/{id}")
    public Response<CustomerResponse> getCustomerById(@PathVariable UUID id) {
        return respond(Mapper.toResponse(customerService.getCustomerById(id)));
    }

    // GET /customers?email=
    @GetMapping(params = "email")
    public Response<CustomerResponse> getCustomerByEmail(@RequestParam String email) {
        return respond(Mapper.toResponse(customerService.getCustomerByEmail(email)));

    }

    // GET /customers?name=
    @GetMapping(params = "name")
    public Response<CustomerResponse> getCustomerByName(@RequestParam String name) {
        return respond(Mapper.toResponse(customerService.getCustomerByName(name)));
    }

    // PUT /customers/{id}
    @PutMapping("/{id}")
    public Response<CustomerResponse> updateCustomer(@PathVariable UUID id,
                                                     @Valid @RequestBody CustomerRequest request) {
        return respond(Mapper.toResponse(customerService.updateCustomer(id,Mapper.toDto(request))));
    }

    // DELETE /customers/{id}
    @DeleteMapping("/{id}")
    public Response<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return new Response<>(MetaResponse.success());
    }
}