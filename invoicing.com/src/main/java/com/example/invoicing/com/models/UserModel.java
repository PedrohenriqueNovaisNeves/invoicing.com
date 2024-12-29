package com.example.invoicing.com.models;

import jakarta.persistence.*;
import java.io.Serializable;
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
}
