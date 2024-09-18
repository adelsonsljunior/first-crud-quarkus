package com.adelsonsljunior.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "User request object")
public record UserRequestDTO(
        @NotBlank(message = "{user.name.not.blank}")
        @Schema(description = "Name of the user", example = "Marceline")
        String username,
        @NotBlank(message = "{user.email.not.blank}")
        @Email(message = "{user.email.email}")
        @Schema(description = "Email of the user", example = "marceline@ooo.com")
        String email,
        @NotBlank(message = "{user.password.not.blank}")
        @Size(min = 8, message = "{user.password.size}")
        @Schema(description = "Password of the user", example = "12345678")
        String password) {
}
