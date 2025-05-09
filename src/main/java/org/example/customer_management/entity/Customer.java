package org.example.customer_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers")
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(precision = 10, scale = 2)
    private BigDecimal annualSpend;

    private LocalDateTime lastPurchaseDate;

    public Customer(UUID id, String name, String email, BigDecimal annualSpend, LocalDateTime lastPurchaseDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.annualSpend = annualSpend;
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public Customer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public BigDecimal getAnnualSpend() {
        return annualSpend;
    }

    public void setAnnualSpend(BigDecimal annualSpend) {
        this.annualSpend = annualSpend;
    }

    public LocalDateTime getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(LocalDateTime lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }
}
