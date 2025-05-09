package org.example.customer_management.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Generic response model for API responses.
 * This class encapsulates both the actual response data and metadata about the response.
 *
 * @param <T> The type of the response data.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T>{
    private T data;
    private MetaResponse meta;

    public Response(MetaResponse meta) {
        this.meta = meta;
    }

    public Response (T data) {
        this.data = data;
        this.meta = MetaResponse.success();
    }

    @Override
    public String toString() {
        return "data: " + (data != null ? data.toString() : "null") + ", meta: " + (meta != null ? meta.toString() : "null");
    }
}
