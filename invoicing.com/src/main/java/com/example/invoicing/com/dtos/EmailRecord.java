package com.example.invoicing.com.dtos;

public class EmailRecord {

    private String para;
    private String assunto;
    private String corpo;

    public String getPara() {
        return para;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
}
