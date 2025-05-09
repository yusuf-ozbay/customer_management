package org.example.customer_management.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * Generic response model for paginated data.
 * Uses a generic type parameter <T> to support various data types.
 * Wraps data in a Page<T> structure to handle pagination efficiently.
 * Implements a custom toString() method for better readability.
 */


public class PageResponse<T>{
    private Page<T> items = Page.empty();

    public PageResponse(Page<T> items) {
        this.items = items;
    }

    public PageResponse() {
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("pageItems: ");
        if(items!=null && !items.isEmpty()){
            items.forEach(builder::append);
        }
        return builder.toString();
    }

    public Page<T> getItems() {
        return items;
    }

    public void setItems(Page<T> items) {
        this.items = items;
    }
}
