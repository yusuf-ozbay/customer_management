package org.example.customer_management.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Generic response model for handling lists of data items.
 * Uses a generic type parameter <T> to support various data types.
 * Implements a custom toString() method for better readability.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> {
    private List<T> items = List.of();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("listItems: ");
        if (items != null) {
            items.forEach(item -> {
                sb.append(item.toString());
                sb.append('\n');
            });
        }
        return sb.toString();
    }
}
