package com.example.invoicing.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @Column(nullable = false)
    private boolean paid;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonIgnore
    private UserModel user;

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

    public Priority getPriority() {
        return priority;
    }

    public boolean isPaid() {
        return paid;
    }

    public UserModel getUser() {
        return user;
    }

    public void setStatus(String status){
        if(status.equalsIgnoreCase("pago")){
            this.paid = true;
        }
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

    public void setDescriptionInvoice(String descriptionInvoice){
        this.descriptionInvoice = descriptionInvoice;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UUID getIdUser(){
        return user != null ? user.getIdUser() : null;
    }
}
