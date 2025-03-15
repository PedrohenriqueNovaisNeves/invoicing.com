package com.example.invoicing.com.dtos;

import com.example.invoicing.com.models.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.*;

public record InvoiceRecord(@NotBlank String title, @NotNull BigDecimal value, @NotBlank String descriptionInvoice, @NotNull Date maturity, @NotNull boolean paid, @NotNull Priority priority){
}
