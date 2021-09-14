package com.fax.lekari.dto;

public class PregledDtoRes {
    private int id;
    private String podaciOPregledu;
    private int popust;
    private int trajanje;
    private String vreme;
    private String nazivUsluge;
    private int cenaUsluge;
    private String lekar;
    private String nazivKlinike;
    private String adresaKlinike;
    private String pacijent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPodaciOPregledu() {
        return podaciOPregledu;
    }

    public void setPodaciOPregledu(String podaciOPregledu) {
        this.podaciOPregledu = podaciOPregledu;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public int getCenaUsluge() {
        return cenaUsluge;
    }

    public void setCenaUsluge(int cenaUsluge) {
        this.cenaUsluge = cenaUsluge;
    }

    public String getLekar() {
        return lekar;
    }

    public void setLekar(String lekar) {
        this.lekar = lekar;
    }

    public String getNazivKlinike() {
        return nazivKlinike;
    }

    public void setNazivKlinike(String nazivKlinike) {
        this.nazivKlinike = nazivKlinike;
    }

    public String getAdresaKlinike() {
        return adresaKlinike;
    }

    public void setAdresaKlinike(String adresaKlinike) {
        this.adresaKlinike = adresaKlinike;
    }

    public String getPacijent() {
        return pacijent;
    }

    public void setPacijent(String pacijent) {
        this.pacijent = pacijent;
    }
}
