package com.adelsonsljunior.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class BadRequestResponse{

    @JsonProperty("errors")
    private Map<String, String> errors;

    public BadRequestResponse(Map<String, String> errors) {
        this.errors = errors;
    }
}
