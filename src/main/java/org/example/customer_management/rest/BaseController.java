package org.example.customer_management.rest;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Base controller class that provides common response handling methods.
 * This class simplifies API response generation by using `ResponseBuilder`.
 *
 * Instead of manually calling `ResponseBuilder` in each controller,
 * controllers can extend this class and use the `respond()` methods directly.
 */

public class BaseController {

    public <T> Response<DataResponse<T>> respond(List<T> items) {
        return ResponseBuilder.build(items);
    }

    public <T> Response<PageResponse<T>> respond(Page<T> items) {
        return ResponseBuilder.build(items);
    }

    public <T> Response<T> respond(T item) {
        return ResponseBuilder.build(item);
    }

    public Response<MetaResponse> respond(MetaResponse metaResponse) {
        return ResponseBuilder.build(metaResponse);
    }
}
