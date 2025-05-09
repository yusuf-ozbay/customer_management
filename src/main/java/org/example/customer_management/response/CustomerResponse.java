package org.example.customer_management.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerResponse {

    private UUID id;
    private String name;
    private String email;
    private BigDecimal annualSpend;
    private LocalDateTime lastPurchaseDate;
    private String tier;
}