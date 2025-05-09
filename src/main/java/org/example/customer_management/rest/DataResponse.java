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


public class DataResponse<T> {
    private List<T> items = List.of();

    public DataResponse(List<T> items) {
        this.items = items;
    }

    public DataResponse() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("dataItems: ");
        if (items != null && !items.isEmpty()) {
            items.forEach(builder::append);
        }
        return builder.toString();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
