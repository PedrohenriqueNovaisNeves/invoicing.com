package com.example.invoicing.com.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecord(@NotBlank String nameUser, @NotBlank String CPF, @NotBlank String RG, @NotBlank String email) {
}
