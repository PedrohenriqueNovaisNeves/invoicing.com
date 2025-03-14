package com.example.invoicing.com.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import java.sql.*;

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

    public UUID getIdInvoice() {
        return idInvoice;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescriptionInvoice() {
        return descriptionInvoice;
    }

    public Date getMaturity() {
        return maturity;
    }

    public String getInvoiceCod() {
        return invoiceCod;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setIdInvoice(UUID idInvoice) {
        this.idInvoice = idInvoice;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setDescriptionInvoice(String descriptionInvoice) {
        this.descriptionInvoice = descriptionInvoice;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }

    public void setInvoiceCod(String invoiceCod) {
        this.invoiceCod = invoiceCod;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
