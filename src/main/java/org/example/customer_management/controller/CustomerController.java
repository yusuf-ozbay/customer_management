package org.example.customer_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

    @PostMapping
    @Operation(
            summary = "Müşteri Oluştur",
            description = "Yeni bir müşteri kaydı oluşturur."
    )
    @ApiResponse(responseCode = "200", description = "Müşteri başarıyla oluşturuldu.")
    public Response<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        return respond(Mapper.toResponse(customerService.createCustomer(Mapper.toDto(request))));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Müşteriyi ID ile Getir",
            description = "Belirtilen ID'ye sahip müşteriyi getirir."
    )
    @ApiResponse(responseCode = "200", description = "Müşteri başarıyla getirildi.")
    public Response<CustomerResponse> getCustomerById(@PathVariable UUID id) {
        return respond(Mapper.toResponse(customerService.getCustomerById(id)));
    }

    @GetMapping(params = "email")
    @Operation(
            summary = "Email ile Müşteri Getir",
            description = "Belirtilen e-posta adresine sahip müşteriyi getirir."
    )
    @ApiResponse(responseCode = "200", description = "Müşteri başarıyla getirildi.")
    public Response<CustomerResponse> getCustomerByEmail(@RequestParam String email) {
        return respond(Mapper.toResponse(customerService.getCustomerByEmail(email)));
    }

    @GetMapping(params = "name")
    @Operation(
            summary = "İsim ile Müşteri Getir",
            description = "Belirtilen isme sahip müşteriyi getirir."
    )
    @ApiResponse(responseCode = "200", description = "Müşteri başarıyla getirildi.")
    public Response<CustomerResponse> getCustomerByName(@RequestParam String name) {
        return respond(Mapper.toResponse(customerService.getCustomerByName(name)));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Müşteri Güncelle",
            description = "ID bilgisine göre mevcut müşteri kaydını günceller."
    )
    @ApiResponse(responseCode = "200", description = "Müşteri başarıyla güncellendi.")
    public Response<CustomerResponse> updateCustomer(@PathVariable UUID id,
                                                     @Valid @RequestBody CustomerRequest request) {
        return respond(Mapper.toResponse(customerService.updateCustomer(id, Mapper.toDto(request))));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Müşteri Sil",
            description = "Belirtilen ID'ye sahip müşteri kaydını siler."
    )
    @ApiResponse(responseCode = "200", description = "Müşteri başarıyla silindi.")
    public Response<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return new Response<>(MetaResponse.success());
    }
}
