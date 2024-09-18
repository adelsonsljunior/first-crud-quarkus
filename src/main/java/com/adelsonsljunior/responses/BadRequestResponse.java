package com.adelsonsljunior.responses;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Map;

@Schema(description = "Represents a bad request response containing a map of error details")
public record BadRequestResponse(
        @Schema(description = "A map of error fields and their respective messages",
                example = """
                        {
                          "create.data.password": "User password must not be blank.",
                          "create.data.username": "Username must not be blank.",
                          "create.data.email": "User email must be valid."
                        }""")
        Map<String, String> errors) {
}
