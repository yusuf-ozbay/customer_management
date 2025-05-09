package org.example.customer_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customers")
//@EqualsAndHashCode(of = "id")
public class Customer {
//
//    @Id
//    @Column(nullable = false, updatable = false, unique = true)
//    @GenericGenerator(name= "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @GeneratedValue(generator = "UUID")
//    private String id;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(precision = 10, scale = 2)
    private BigDecimal annualSpend;

    private LocalDateTime lastPurchaseDate;



    public Customer(@NotBlank(message = "Name is required") String name, @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email, BigDecimal annualSpend, LocalDateTime lastPurchaseDate) {
    }

    public Customer() {

    }
}
