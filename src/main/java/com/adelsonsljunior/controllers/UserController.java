package com.adelsonsljunior.controllers;

import com.adelsonsljunior.dtos.UserRequestDTO;
import com.adelsonsljunior.dtos.UserResponseDTO;
import com.adelsonsljunior.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path(value = "/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public Response findAll() {

        List<UserResponseDTO> users = userService.findAll();
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @POST
    public Response create(UserRequestDTO data) {

        UserResponseDTO createdUser = userService.create(data);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @GET
    @Path(value = "/{id}")
    public Response findById(@PathParam("id") Long id) {

        UserResponseDTO user = userService.findById(id);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @PATCH
    @Path(value = "/{id}")
    public Response update(@PathParam("id") Long id, UserRequestDTO data) {

        UserResponseDTO updatedUser = userService.update(id, data);
        return Response.status(Response.Status.OK).entity(updatedUser).build();
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam("id") Long id) {

        userService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
