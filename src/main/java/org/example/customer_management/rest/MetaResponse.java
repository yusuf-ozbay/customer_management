package org.example.customer_management.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.customer_management.enums.MessageCodes;

/**
 * MetaResponse model class that represents a standardized response structure.
 * It includes a description and a response code.
 * Provides utility methods for success and failure responses.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MetaResponse {

    private String code;
    private String description;

    public static MetaResponse of(String code, String description) {
        return new MetaResponse(code, description);
    }

    public static MetaResponse success() {
        return new MetaResponse(MessageCodes.SUCCESS.getCode(), "Success");
    }

    public static MetaResponse fail(String description) {
        return new MetaResponse(MessageCodes.FAIL.getCode(), description);
    }


}
