package com.adelsonsljunior.exceptions;

import com.adelsonsljunior.responses.ErrorResponse;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExistingEmailMapper implements ExceptionMapper<ExistingEmailException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ExistingEmailException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return Response.status(Response.Status.SEE_OTHER)
                .entity(response)
                .build();
    }
}
