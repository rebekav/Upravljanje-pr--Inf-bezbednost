package com.fax.lekari.dto;

public class ZdravstveniKartonDtoReq {
    private String bolest;

    private String izvestaj;

    private String terapija;

    private int idPregled;

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

    public String getTerapija() {
        return terapija;
    }

    public void setTerapija(String terapija) {
        this.terapija = terapija;
    }

    public int getIdPregled() {
        return idPregled;
    }

    public void setIdPregled(int idPregled) {
        this.idPregled = idPregled;
    }
}
