package com.example.invoicing.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;
    private String nameUser;
    private String CPF;
    private String RG;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InvoiceModel> invoices;

    public UUID getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getCPF() {
        return CPF;
    }

    public String getRG() {
        return RG;
    }

    public String getEmail() {
        return email;
    }

    public List<InvoiceModel> getInvoices() {
        return invoices;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInvoices(List<InvoiceModel> invoices) {
        this.invoices = invoices;
    }

    public int getTotalInvoices(){
        return invoices != null ? invoices.size() : 0;
    }

}
