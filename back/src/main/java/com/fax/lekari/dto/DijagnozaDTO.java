package com.fax.lekari.dto;

public class DijagnozaDTO {
    private String bolest;

    private String izvestaj;

    public DijagnozaDTO(String bolest, String izvestaj) {
        this.bolest = bolest;
        this.izvestaj = izvestaj;
    }

    public String getBolest() {
        return bolest;
    }

    public void setBolest(String bolest) {
        this.bolest = bolest;
    }

    public String getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(String izvestaj) {
        this.izvestaj = izvestaj;
    }
}
