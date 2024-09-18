package com.adelsonsljunior.exceptions;

import com.adelsonsljunior.responses.BadRequestResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

@Provider
public class BadRequestMapper implements ExceptionMapper<ConstraintViolationException> {


    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ConstraintViolationException e) {

        Map<String, String> errors = new HashMap<>();

        // // Extraindo as mensagens de erro das violações
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        }

        BadRequestResponse response = new BadRequestResponse(errors);

        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
