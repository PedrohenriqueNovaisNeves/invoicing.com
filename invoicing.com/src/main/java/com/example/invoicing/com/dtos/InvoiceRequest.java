package com.example.invoicing.com.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvoiceRequest(@NotBlank String nameUser, @NotNull InvoiceRecord invoiceRecord) {
}
