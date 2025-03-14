package com.example.invoicing.com.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import java.sql.*;

@Getter
@Setter
@Entity
@Table(name = "TB_INVOICE")
public class InvoiceModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID idInvoice;
    private String title;
    private BigDecimal value;
    private String descriptionInvoice;
    private Date maturity;
    @Column(unique = true)
    private String invoiceCod;
    @Column(nullable = false)
    private boolean paid;
}
