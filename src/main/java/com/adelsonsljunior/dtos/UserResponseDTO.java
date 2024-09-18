package com.adelsonsljunior.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "User response object")
public record UserResponseDTO(
        @Schema(description = "User ID", example = "1")
        Long id,
        @Schema(description = "Name of the user", example = "Marceline")
        String username,
        @Schema(description = "Email of the user", example = "marceline@ooo.com")
        String email,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        @Schema(description = "The creation date and time of the user", example = "2024-09-18T12:25:41Z", format = "date-time")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        @Schema(description = "The last update date and time of the user", example = "2024-09-18T12:25:41Z", format = "date-time")
        LocalDateTime updatedAt
) {
}
