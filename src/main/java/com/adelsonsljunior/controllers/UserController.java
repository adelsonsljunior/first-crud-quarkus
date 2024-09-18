package com.adelsonsljunior.controllers;

import com.adelsonsljunior.dtos.UserRequestDTO;
import com.adelsonsljunior.dtos.UserResponseDTO;
import com.adelsonsljunior.responses.BadRequestResponse;
import com.adelsonsljunior.responses.ErrorResponse;
import com.adelsonsljunior.services.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path(value = "/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User", description = "Operations related to Users")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Operation(summary = "Get all users", description = "Returns a list of users")
    @APIResponse(
            responseCode = "200",
            description = "List of users",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = UserResponseDTO.class)

            )
    )
    public Response findAll() {

        List<UserResponseDTO> users = userService.findAll();
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @POST
    @Operation(summary = "Create a new user", description = "Creates a new user")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "201",
                            description = "User created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = UserResponseDTO.class)
                            )
                    ),
                    @APIResponse(
                            responseCode = "303",
                            description = "Existing user resource",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @APIResponse(
                            responseCode = "400",
                            description = "Invalid request. The data provided is not valid or missing required fields",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = BadRequestResponse.class)
                            )
                    )
            }
    )
    public Response create(@Valid UserRequestDTO data) {

        UserResponseDTO createdUser = userService.create(data);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @GET
    @Path(value = "/{id}")
    @Operation(summary = "Find user by ID", description = "Returns a user by their ID")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "User found successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = UserResponseDTO.class)
                            )
                    ),
                    @APIResponse(
                            responseCode = "404",
                            description = "User not found with the given ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    public Response findById(@PathParam("id") Long id) {

        UserResponseDTO user = userService.findById(id);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @PATCH
    @Path(value = "/{id}")
    @Operation(summary = "Update user by ID", description = "Updates the details of a user with the given ID")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "User successfully updated",
                    content = @Content(
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            ),
            @APIResponse(
                    responseCode = "303",
                    description = "Existing user resource",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @APIResponse(
                    responseCode = "400",
                    description = "User not found with the given ID",
                    content = @Content(
                            schema = @Schema(implementation = BadRequestResponse.class)
                    )
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Invalid request. The data provided is not valid or missing required fields",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Response update(@PathParam("id") Long id, @Valid UserRequestDTO data) {

        UserResponseDTO updatedUser = userService.update(id, data);
        return Response.status(Response.Status.OK).entity(updatedUser).build();
    }

    @DELETE
    @Path(value = "/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user with the given ID")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "204",
                    description = "User successfully deleted"
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "User not found with the given ID",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Response delete(@PathParam("id") Long id) {

        userService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
