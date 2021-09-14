package com.fax.lekari.dto;


public class ZdravstveniKartonDtoRes {
    private int id;

    private String bolest;

    private String izvestaj;

    private byte overen;

    private String terapija;

    private String vreme;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte getOveren() {
        return overen;
    }

    public void setOveren(byte overen) {
        this.overen = overen;
    }

    public String getTerapija() {
        return terapija;
    }

    public void setTerapija(String terapija) {
        this.terapija = terapija;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

}
